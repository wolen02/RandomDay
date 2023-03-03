package com.wolen.randomday.domestic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/domestic")
public class DomesticController {

	
	@RequestMapping("/select")
	public String select() {
		return"/randomday/domestic/select";
	}
	
}
