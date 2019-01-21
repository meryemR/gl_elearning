package org.mql.controllers;

import java.util.List;
import java.util.Vector;

import org.mql.dao.FormationRepository;
import org.mql.dao.MemberRepository;
import org.mql.dao.ModuleRepository;
import org.mql.dao.StreamingRepository;
import org.mql.dao.TimingRepository;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.models.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@Autowired
	FormationRepository formationRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	StreamingRepository streamingRepository;

	@Autowired
	TimingRepository timingRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewUser() {
		List<Formation> formations= new Vector<>();
		Formation formation1 = new Formation("JAVA");
		formation1.setCategory("Enterprise Edition Programming Lanugages");
		formation1.setCreatingDate("15/03/2014");
		
		Formation formation2 = new Formation("C++");		
		formation2.setCategory("C++ Games Dev");
		formation2.setCreatingDate("05/04/2017");
		
		
		Formation formation3 = new Formation("C#");		
		formation3.setCategory("Microsoft Programming Languages");
		formation3.setCreatingDate("10/11/2018");
		
		formations.add(formation1);
		formations.add(formation2);
		formations.add(formation3);
		
		
		List<Member> members = new Vector<>();
		Member member = new Member();
		member.setFirstName("Khalid");
		member.setLastName("Chahboune");
		member.setEmail("Khalidqqr@gmail.com");
		
		Member member0 = new Member();
		member0.setFirstName("Chaimae");
		member0.setLastName("Zarhouni");
		member0.setEmail("Chaimaeqqr@gmail.com");
		members.add(member0);
		members.add(member);
		
		members.add(new Member("Hicham","Haydar"));
		members.add(new Member("Mohamed","Zaraoui"));
		members.add(new Member("Brakani","Karim"));
		members.add(new Member("Elhaddady","Youssef"));
		
		
		// ajouter certains modules pour le test
		List<Module> modules = new Vector<>();
		modules.add(new Module("Java", new Member("khalid")));
		modules.add(new Module("Oracle", new Member("omar")));
		modules.add(new Module("XML", new Member("hicham")));
		modules.add(new Module("TEC", new Member("Douhi")));
		modules.add(new Module("Génie Logiciel", new Member("Mouad")));
		
		moduleRepository.saveAll(modules);
		
		memberRepository.saveAll(members);
		formationRepository.saveAll(formations);
		
		return "Saved";
	}
	
	@GetMapping(path = "/add2")
	public @ResponseBody String addNewMember() {
		Member member = new Member("Soumaya", "Bahij", "soumaya@gmail.com", "");
		member.setPassword(new BCryptPasswordEncoder(10).encode("soumaya"));
		memberRepository.save(member);
		return "added!";
	}
	
	

	@GetMapping("/")
	public String home() {
		return "main_views/home";
	}
	
	@GetMapping("/articles")
	public String articles() {
		return "main_views/articles";
	}
	
	@GetMapping("/contacts")
	public String contacts() {
		return "main_views/contacts";
	}
	

	@GetMapping("/register")
	public String register() {
		return "main_views/register";
	}
	
//	@GetMapping("/stream")
//	public String stream() {
//		return "streamVideo";
//	}

	@GetMapping(path = "/allMembers")
	public @ResponseBody String getAllMembers() {
		return memberRepository.findAll().toString();
	}

	@GetMapping(path = "/allFormations")
	public @ResponseBody String getAllFormations() {
		return formationRepository.findAll().toString();
	}

	@GetMapping(path = "/allModules")
	public @ResponseBody String getAllModules() {

		return moduleRepository.findAll().toString();
	}

	@GetMapping(path = "/allStreamings")
	public @ResponseBody String getAllStreamings() {
		return streamingRepository.findAll().toString();
	}

	@GetMapping(path = "/allTimings")
	public @ResponseBody String getAllTimings() {
		return timingRepository.findAll().toString();
	}

	@GetMapping(path = "/delete")
	public @ResponseBody String delete() {
		formationRepository.deleteAll();
		return "deleted";
	}

}
