package com.wtp;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.wtp.model.Member;

@Controller("memberController")
@RequestMapping("/member")
@SessionAttributes("member")
public class MemberController {
	@Autowired
	private MemberBO memberBO;
	
	@RequestMapping("/list")
	public void list(Model model) throws SQLException {
		model.addAttribute("list", memberBO.list());
	}
	
	@RequestMapping("/form")
	public void form(Model model) {
		model.addAttribute("member", new Member());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String formSubmit(@Valid Member member, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return "member/form";
		}
		memberBO.add(member);
		return "redirect:/member/list";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateForm(@PathVariable int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", memberBO.get(Long.parseLong(String.valueOf(id))));
		mav.setViewName("member/update");
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateSubmit(@Valid Member member, BindingResult result, SessionStatus status) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			return "redirect:/member/list";
		}
		
		memberBO.update(member);
		status.setComplete();
		
		return "redirect:/member/list";		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int id) throws Exception {
		memberBO.delete(id);
		return "redirect:/member/list";
	}
}
