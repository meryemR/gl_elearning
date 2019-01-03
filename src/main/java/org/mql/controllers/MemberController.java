package org.mql.controllers;

import javax.validation.Valid;

import org.mql.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

		@Autowired
		private org.mql.dao.MemberRepository memberRepository;
		
		
		@RequestMapping(value="inscrire" ,method=RequestMethod.GET)
		public String New(Model model) {//
			model.addAttribute("member", new Member());
			return "main_views/register";
		
		}
		
		@RequestMapping(value="/ProfileStudent",method=RequestMethod.POST)
		public String save(Model model,@Valid Member member, BindingResult bindingResult)
		{
			if(bindingResult.hasErrors())
			{
				return "register" ;
			}
			memberRepository.save(member);
			return "main_views/ProfileStudent" ;
		}

		
}
