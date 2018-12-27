package org.mql.controllers;

import java.util.List;

import org.mql.dao.FormationRepository;
import org.mql.models.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FormationController {

	@Autowired
	FormationRepository formationRepository;
	
	@GetMapping("/formation")
	public String getFormations(Model model) {
		List<Formation> formations = formationRepository.findAll();
		model.addAttribute("formations", formations);
		return "formationlist";
	}
	
	@GetMapping("formation/{id}/addmodule")
	public String addModule(@PathVariable int id) {
		
		return "addmodule";
	}
}
