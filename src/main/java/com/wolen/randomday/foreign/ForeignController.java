package com.wolen.randomday.foreign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randomday")
public class ForeignController {

	
	@RequestMapping("foreign/select/view")
	public String select() {
		
		return"/randomday/foreign/select";
		
	}
	
}
