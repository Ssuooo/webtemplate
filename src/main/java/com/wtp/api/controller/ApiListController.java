package com.wtp.api.controller;

import javax.annotation.Resource;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wtp.api.bo.ApiListBO;

/**
 *  
 *	TODO 
 * 	저장된 api를 restful 로 가져오기 
 */

@Controller
@RequestMapping("/api")
public class ApiListController {
	
	@Resource(name="apiListBO")
	private ApiListBO apiListBO;

	// 해당 지역코드에 해당하는 매매내역 목록 가져오기 
	@RequestMapping("/get/regioCode/{regionCode}")
	public String getListbyRegionCode(@RequestParam String regionCode, Model model) throws Exception {
		HashMap param = new HashMap();
		param.put("regionCode", regionCode);
		model.addAttribute("dealList", apiListBO.selectApartDealingCostList(param));
		return "/api/apiList";
//		ModelAndView mav = new ModelAndView();
		
//		mav.setViewName("/info");
//		return mav;
	}
	
	@RequestMapping("/apiList")
	public String getList(Model model) throws Exception {
//		model.addAttribute("dealList", apiListBO.selectApartDealingCostList(param));
		return "/api/apiList";
//		ModelAndView mav = new ModelAndView();
		
//		mav.setViewName("/info");
//		return mav;
	}
	
	 
	
	// get/deposit/30000 : 보증금 30000만원 미만
	
	// get/monthlyRent/30 : 월 30만원 미만
}
