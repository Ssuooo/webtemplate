package com.wtp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public void hello(@RequestParam String name, Model model) {
		model.addAttribute("hello", "Hello! "+ name);
//		return "hello";
	}
}
