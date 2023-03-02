package com.wolen.randomday.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldJspController {

	@RequestMapping("/helloWorldJsp")
	public String helloWorld() {
		return "test/helloWorld";
	}
	
}
