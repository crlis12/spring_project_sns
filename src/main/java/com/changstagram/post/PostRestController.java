package com.changstagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.changstagram.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글쓰기
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/create")
	public Map<String, Object> addPost(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		
		
		// session 가져오기
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		String loginId = (String) session.getAttribute("loginId");
		
		Map<String, Object> result = new HashMap<>();
		result.putIfAbsent("result", "success");
		
		
		// userId userLoginId content file -> BO insert 요청
		// DB
		int row = postBO.addPost(userId, loginId, content, file);
		
		if(row < 1) {
			result.put("result", "error");
			result.put("errorMessage", "에러가 발생했습니다. 다시 작성해주세요");
		}
		
		
		return result;			
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		
		Map<String,Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			logger.error("[post delete] not found login session postId:{}", postId);
			result.put("result", "error");
			result.put("error_message", "로그인을 다시해주세요");
			return result;
		}
		
		// TODO delete db
		int row = postBO.deletePost(postId, userId);
		
		if(row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "error");
			result.put("error_message","삭제에 실패했습니다.");
		}
		
		return result;
		
	}
	
}
