package org.mql.controllers;

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
		model.addAttribute("modules", formation.getModules() );
		return "formation";
	}
	
	@GetMapping("/module/{id}")
	public String getModule(@PathVariable int id,Model model) {
		Module module = moduleRepository.findById(id).get();
		model.addAttribute("module", module);
		return "module";
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
	
	@PostMapping("addModule")
	public String addModule(@ModelAttribute Module module,@RequestParam("formation_id") int id,@RequestParam("member_id") int memberId,Model model) {
		Formation formation = formationRepository.findById(id).get();
		Member member = memberRepository.findById(memberId).get();
		module.setTeacher(member);
		formation.add(module);
		formationRepository.save(formation);
		return "redirect:/dashboard/formation";
	}
	

	//CODE HAJAR : ajouter une formation via une formulaire**************************************************
	@GetMapping(value="/FormulaireFormation")
	public String FormulaireFormation(Model model)
	{
		model.addAttribute("formation",new Formation());
		model.addAttribute("member",new Member());
		return "admin_section/add-listing" ;
	}
	//affichage de la formation ajoutee**********************************************************************
	@PostMapping(value="/save")
	public String save(Model model,@Valid Formation formation,Member member, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "admin_section/add-listing" ;
		}
		//formationRepository.save(formation);
		member=memberRepository.findByEmail(member.getEmail());
		formation.setCreator(member);
		formationRepository.save(formation);
		return "admin_section/course-detail" ;
	}
	//page dans l'acceuil pour afficher toutes les formations*************************************************
	@GetMapping(value="/listeFormations")
	public String index(Model model)
	{
		List<Formation> Formations=formationRepository.findAll();
		model.addAttribute("listeFormations",Formations);	
		return "main_views/courses-list" ;
	}
	
}
