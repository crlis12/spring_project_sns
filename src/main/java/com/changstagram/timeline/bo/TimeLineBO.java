package com.changstagram.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.changstagram.comment.bo.CommentBO;
import com.changstagram.comment.model.CommentView;
import com.changstagram.post.bo.PostBO;
import com.changstagram.post.model.Post;
import com.changstagram.timeline.model.CardView;
import com.changstagram.user.bo.UserBO;

public class TimeLineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 타임라인 화면의 경우 비로그인일 떄도 보여져야 하므로 Integer userId
	public List<CardView> generateCardViewList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 List 가져온다.
		List<Post> postList = postBO.getPostList();
		for(Post post: postList) {
			CardView card = new CardView();
			
			// 글 정보
			card.setPost(post);
			
			// 댓글들 정보
			List<CommentView> commentList = commentBO.getCommentListByPostId(post.getId());
			//card.setCommentList(commentList);
			
			cardViewList.add(card);
			
			//좋아요 카운트
			// 지금 좋아요 눌렀는지 여부
			
			// 글쓴이 정보  DB부하가 걸릴수 있으면 캐쉬를 활용
			//User user = userBO.getUserByUserId(post.getUserId());
			//card.setUser(user);
		}
		
		return cardViewList;
	}
}
