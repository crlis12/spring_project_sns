package com.changstagram.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		return "Hello World";
	}

	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> resultQuiz = new HashMap<String, Object>();
		
		resultQuiz.put("a", 1);
		resultQuiz.put("b", 2);
		resultQuiz.put("c", 3);
		resultQuiz.put("d", 4);
		resultQuiz.put("e", 5);
		
		return resultQuiz;
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "test/quiztest";
	}
}
