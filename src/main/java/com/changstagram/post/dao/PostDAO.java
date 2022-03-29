package com.changstagram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.changstagram.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Post> selectPostList();
	
	public int insertPost(
			@Param("userId") int userId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
}
