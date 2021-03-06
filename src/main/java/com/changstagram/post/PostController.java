package com.changstagram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.changstagram.post.bo.PostBO;
import com.changstagram.post.model.Post;


@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO; 
	
	@RequestMapping("/post_list_view")
	public String postListView(Model model)	{
		model.addAttribute("viewName", "post/post_list");
		
		return "template/layout";
	}
}
