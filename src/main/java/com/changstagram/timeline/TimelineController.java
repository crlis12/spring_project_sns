package com.changstagram.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.changstagram.post.bo.PostBO;
import com.changstagram.post.model.Post;
import com.changstagram.timeline.bo.TimeLineBO;
import com.changstagram.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimeLineBO timelineBO;
	
	@RequestMapping("timeline_view")
	public String timelineView(Model model,
			HttpServletRequest request)	{
		// List<Post> postList = postBO.getPostList();
		//로그인을 안할 시 로그인 화면으로
		HttpSession session = request.getSession();
		//Integer userId = (Integer)session.getAttribute("userId");
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList();
		
		model.addAttribute("postList", postList);
		List<CardView> cardViewList = timelineBO.generateCardViewList(); 
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
	
}
