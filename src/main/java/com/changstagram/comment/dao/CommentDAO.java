package com.changstagram.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.changstagram.comment.model.CommentView;

@Repository
public interface CommentDAO {
	public void insertComment(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public List<CommentView> selectCommentListByPostId(int postId);
}

