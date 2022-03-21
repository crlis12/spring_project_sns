package com.changstagram.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.changstagram.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Post> selectPostList();
}
