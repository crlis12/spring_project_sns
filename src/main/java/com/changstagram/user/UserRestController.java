package com.changstagram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.changstagram.common.EncryptUtils;
import com.changstagram.user.bo.UserBO;
import com.changstagram.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 중복확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDupplicatedId(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		int existCount = userBO.existLoginId(loginId);
		
		if(existCount > 0) {
			result.put("result", true);
		} else {
			result.put("error", false );
		}
		
		return result;
	}
	
	/** 
	 * 회원 가입
	 * @param loginId
	 * @param password
	 * @param name
	 * @param emil
	 * @return
	 */
	@RequestMapping("/sign_up")
	public Map<String, Object> SingUpForAjax(
			@RequestParam("loginId") String loginId,
			@RequestParam("password")String password,
			@RequestParam("name") String name,
			@RequestParam("email") String emil){
		Map<String, Object > result = new HashMap<String, Object>();
		
		String encryptUtils = EncryptUtils.md5(password); //암호화
		
		int row = userBO.addUser(loginId, encryptUtils, name, emil);
		
		if(row == 1 ) {
			result.put("result", "success");
		} else {
			result.put("error", "입력 실패");
		}
		
		return result;
	}
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/sign_in")
	public Map<String, Object> userSignUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		//비밀번호 해싱(암호화)
		String encryptUtils = EncryptUtils.md5(password);
		
		//DB select
		User user = userBO.getUserByLoginIdAndPassword(loginId, encryptUtils);
		
		Map<String, Object> result = new HashMap<>();
		
		if(user != null) {
			result.put("result", "success");
			
		} else {
			result.put("result", "error");
			result.put("errorMessage", "존재하지 않는 사용자 입니다.");
		}
		
		// 세션에 로그인 정보 저장(로그인 상태 유지)어디서든지 사용가능
		HttpSession session = request.getSession();
		session.setAttribute("userId", user.getId());
		session.setAttribute("loginId", user.getLoginId());
		session.setAttribute("userName", user.getName());
		session.setAttribute("userEmail", user.getEmail());
		
		return result;
	}
	
	@PostMapping("/changePwd")
	public Map<String, Object> userChangePwd(
			@RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword,
			HttpSession session) {
		
		// 비밀번호 해싱(암호화)
		String encryptUtils = EncryptUtils.md5(password);
		// 새로운 비밀번호 해싱(암호화)
		String encryptUtilsNewPassword = EncryptUtils.md5(newPassword);
		
		
		String userloginId = (String)session.getAttribute("loginId");
		
		Map<String, Object> result = new HashMap<>();
		
		// DB select
		// 파리미터값으로 넘어온 비밀번호가 맞는지 확인
		User user = userBO.getUserByLoginIdAndPassword(userloginId, encryptUtils);
		
		// 유저 정보가 조회가 되면 비밀번호 변경
		if(user != null) {
			// 업데이트
			userBO.upadateUser(userloginId, encryptUtilsNewPassword);
			result.put("result", "success");
		} else {
			result.put("result", "error");
			result.put("error_message", "현재 비밀번호를 다시 입력해주세요.");
		}
		return result;
	}
}
