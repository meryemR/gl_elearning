package org.mql.controllers;

import java.util.List;

import org.mql.dao.FormationRepository;
import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/formation")
	public String getFormations(Model model) {
		List<Formation> formations = formationRepository.findAll();
		model.addAttribute("formations", formations);
		return "formationlist";
	}
	
	@GetMapping("/formation/{id}")
	public String getFormation(@PathVariable int id,Model model) {
		Formation formation = formationRepository.findById(id).get();
		model.addAttribute("formation", formation);
		model.addAttribute("member",memberRepository.findById(1).get());
		model.addAttribute("modules", formation.getModules() );
		return "formation";
	}
	
	@GetMapping("/module/{id}")
	public String getModule(@PathVariable int id,Model model) {
		Module module = moduleRepository.findById(id).get();
		model.addAttribute("module", module);
		return "module";
	}
	
	@GetMapping("/form/{id}")
	public String getForm(@PathVariable int id,Model model) {
		Formation formation =formationRepository.findById(id).get();
		model.addAttribute("formation",formation);
		return "/formations/formation";
	}
	
	@GetMapping("/formation/{id}/add")
	public String ModuleForm(@PathVariable int id,Model model) {
		Formation formation = formationRepository.findById(id).get();
		List<Member> members = memberRepository.findAll();
		System.out.println(members);
		model.addAttribute("members", members);
		model.addAttribute("formation", formation);
		model.addAttribute("module",new Module());
		return "addmodule";
	}
	
	@GetMapping("/formation/{id}/follow")
	public String followFormation(@PathVariable int id,Model model) {
		Member member = memberRepository.findById(1).get();
		Formation formation = formationRepository.findById(id).get();
		if(!formation.getMembers().contains(member)) {
			formation.addMember(member);
			formationRepository.save(formation);	
		}
		return "redirect:/formation/"+id;
	}
	
	@PostMapping("addModule")
	public String addModule(@ModelAttribute Module module,@RequestParam("formation_id") int id,@RequestParam("member_id") int memberId,Model model) {
		Formation formation = formationRepository.findById(id).get();
		Member member = memberRepository.findById(memberId).get();
		module.setTeacher(member);
		formation.add(module);
		formationRepository.save(formation);
		return "redirect:/dashboard/formation";
	}
	
}
