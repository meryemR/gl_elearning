package org.mql.controllers.dashboard;

import java.util.List;

import javax.validation.Valid;

import org.mql.dao.FormationRepository;
import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard")
public class DashFormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/")
	public String mainPage() {
		return "redirect:/dashboard/formation/";
	}
	
	@GetMapping("/formation")
	public String getFormations(Model model) {

		List<Formation> formations = formationRepository.findAllByOrderByIdDesc();
		model.addAttribute("formations", formations);

		return "dashboard/formations";
	}
	

	//CODE HAJAR : ajouter une formation via une formulaire**************************************************
	@GetMapping(value="formation/add")
	public String FormulaireFormation(Model model)
	{
		model.addAttribute("formation",new Formation());
		model.addAttribute("member",new Member());
		return "dashboard/addFormation" ;
	}
	
	//affichage de la formation ajoutee**********************************************************************
	@PostMapping(value="/saveFormation")
	public String save(Model model,@Valid Formation formation,Member member, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "/dashboard/formation/add" ;
		}
		//formationRepository.save(formation);
		member=memberRepository.findByEmail(member.getEmail());
		formation.setCreator(member);
		formationRepository.save(formation);
		return "redirect:/dashboard/formation/";
	}
	
	
	
	@GetMapping("/formation/{id}/add")
	public String ModuleForm(@PathVariable int id,Model model) {
		Formation formation = formationRepository.findById(id).get();
		List<Member> members = memberRepository.findAll();
		System.out.println(members);
		model.addAttribute("members", members);
		model.addAttribute("formation", formation);
		model.addAttribute("module",new Module());
		return "/dashboard/addmodule";
	}
	
	@PostMapping("addModule")
	public String addModule(@ModelAttribute Module module,@RequestParam("formation_id") int id,@RequestParam("member_id") int memberId,Model model) {
		Formation formation = formationRepository.findById(id).get();
		Member member = memberRepository.findById(memberId).get();
		module.setTeacher(member);
		formation.add(module);
		formationRepository.save(formation);
		return "redirect:/dashboard/formation/";
	}
	
}
