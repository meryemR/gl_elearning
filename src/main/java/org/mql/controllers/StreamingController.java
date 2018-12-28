package org.mql.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Vector;

import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.dao.StreamingRepository;
import org.mql.models.Member;
import org.mql.models.Module;
import org.mql.models.Streaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StreamingController {

	@Autowired
	StreamingRepository streamingRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	MemberRepository memberRepository;

	
	@GetMapping("/showStreamForm")
	public String showStreamForm(Model model) {
		// ajouter certains modules pour le test
//		List<Module> test = new Vector<>();
//		test.add(new Module("nanana", new Member("khalid")));
//		test.add(new Module("hvybyh", new Member("omar")));
//		test.add(new Module("hghnj", new Member("hicham")));
//		moduleRepository.saveAll(test);
		
		//Recuperer l'ID de L'enseignant Connecter par le biais des variables de sessions
		
		//Recuperer tt Les modules enseigner par cet enseignant
		// à ameliorer
		List<Module> modules = moduleRepository.findAll();
		//List<Module> modules = memberRepository.findById(1).get().getTeachedModules();

		//remplir un modalAttribute par ces modules 
		model.addAttribute("teachedModules", modules);
		model.addAttribute("streaming",new Streaming());
		
		//test
		System.out.println(modules);
		
		return "main_views/streamForm" ;
	}
	
	@PostMapping("/addStream")
	public  String addStream(@ModelAttribute Streaming streaming) {
		// recuperer les donnees du formulaire dans un bean temporaire
		Module module = moduleRepository.findById(streaming.getModule().getId()).get();
		//ajouter le streaming dans le module correspondant
		
		//Renseigner le champs started time par la date courante
		streaming.setTimeStarted(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		
		module.add(streaming);
		// persister les donnees a la BD
		moduleRepository.saveAndFlush(module);
		
		
		return "redirect:/stream/"+streamingRepository.findFirstByOrderByIdDesc().getId();
		
	}
	
	@GetMapping("stream/{id}")
	public @ResponseBody String showStream(@PathVariable int id ,Model model) {
		// On recupere le stream ainsi que ses attributs 
		//Streaming streaming = streamingRepository.findById(id).get();
		
		return "success "+id;
		//return "main_views/streamVideo";
		
	}

}
