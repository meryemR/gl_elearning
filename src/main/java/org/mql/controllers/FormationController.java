package org.mql.controllers;

import java.util.List;

import javax.validation.Valid;

import org.mql.dao.FormationRepository;
import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/formation/{id}")
	public String getForm(@PathVariable int id,Model model) {
		Formation formation =formationRepository.findById(id).get();
		model.addAttribute("formation",formation);
		return "/formations/formation";
	}
	
	//CODE HAJAR : ajouter une formation via une formulaire**************************************************
	@GetMapping(value="/dashboard/formation/add")
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
	//page dans l'acceuil pour afficher toutes les formations*************************************************
	@GetMapping(value="/formations")
	public String index(Model model)
	{
		List<Formation> Formations=formationRepository.findAllByOrderByIdDesc();
		model.addAttribute("listeFormations",Formations);	
		return "main_views/listformations" ;
	}
	
}
