package com.wtp.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *  
 *	TODO 
 * 	저장된 api를 restful 로 가져오기 
 */

@Controller
@RequestMapping("/api")
public class APIListController {

	@RequestMapping("/get")
	public void get(Model model) {
		model.addAttribute("info", null);	// publicApiBO.get()
//		ModelAndView mav = new ModelAndView();
		
//		mav.setViewName("/info");
//		return mav;
	}
}
