package org.mql.controllers;

import java.util.List;

import org.mql.dao.FormationRepository;
import org.mql.dao.ModuleRepository;
import org.mql.models.Formation;
import org.mql.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
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
		model.addAttribute("formation", formation);
		model.addAttribute("module",new Module());
		return "addmodule";
	}
	
	@PostMapping("addModule")
	public @ResponseBody String addModule(@ModelAttribute Module module,@RequestParam("formation_id") int id,Model model) {
		Formation formation = formationRepository.findById(id).get();
		formation.add(module);
		formationRepository.save(formation);
		return "added";
	}
	
}
