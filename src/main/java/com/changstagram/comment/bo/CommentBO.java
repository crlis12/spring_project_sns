package com.changstagram.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changstagram.comment.dao.CommentDAO;
import com.changstagram.comment.model.CommentView;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	// 댓글 생성
	public void addComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	public List<CommentView> getCommentListByPostId(int postId){
		List<CommentView> resultList = new ArrayList<>();
		resultList = commentDAO.selectCommentListByPostId(postId);
		
		return resultList;
	}
	
	
	
	public List<CommentView> generateCommentViewList(int postId) {
		List<CommentView> resultList = new ArrayList<>();
		// List<Commnet> commnetLIst = getCommentListByPostId(postId)
		
		
		/*
		 * for(Comment comment: commentList) { CommentView commentView = new
		 * CommentView(); // 댓글
		 * 
		 * }
		 */
		
		return resultList;
	}
}
