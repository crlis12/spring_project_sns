package com.changstagram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.changstagram.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Post> selectPostList();
	
	public Post selectPostByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
		
		
	
	// 글 생성
	public int insertPost(
			@Param("userId") int userId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	// 글 삭제
	public int deletePost(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
}
