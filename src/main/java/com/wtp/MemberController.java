package com.wtp;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wtp.model.Member;

@Controller("memberController")
@RequestMapping("/member")
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
	public String formSubmit(@Valid Member member, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "member/form";
		}
		memberBO.add(member);
		return "redirect:/app/member/list";
	}
}
