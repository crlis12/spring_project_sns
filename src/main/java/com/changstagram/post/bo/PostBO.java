package com.changstagram.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.changstagram.comment.bo.CommentBO;
import com.changstagram.common.FileManagerService;
import com.changstagram.post.dao.PostDAO;
import com.changstagram.post.model.Post;


@Service
public class PostBO {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	//select
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	// 유저의 포스트 정보
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		
		return postDAO.selectPostByPostIdAndUserId(postId, userId);
	}
	
	
	
	public int addPost(int userId, String loginId, String content, MultipartFile file) {
		String imagePath = null;
		
		if (file != null) {
			// TODO: 파일매니저 서비스 	input:MultipartFile		output:이미지의 주소
			try {
				imagePath = fileManagerService.saveFile(loginId, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// insert DAO
		return postDAO.insertPost(userId, content, imagePath);
		
	}
	
	// 게시물 삭제
	public int deletePost(int postId, int userId) {
		// select post
		Post post = getPostByPostIdAndUserId(postId, userId);
		
		// post null 검사 => null 이면 logger, 0 return
		if(post == null) {
			logger.error("[deletePost] 게시물이 없습니다. postId:{}", postId);
		}
		
		// 이미지 삭제
		if(post.getImagePath() != null) { // 이미지가 있으면 삭제 
			try {
				fileManagerService.deleteFile(post.getImagePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// 댓글들 삭제
		commentBO.deleteCommentsByPostId(postId);
		// 좋아요들 삭제 byPostId
		
		// 글 삭제
		return postDAO.deletePost(postId, userId);
	}
}
