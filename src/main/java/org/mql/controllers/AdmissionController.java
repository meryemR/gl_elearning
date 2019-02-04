package org.mql.controllers;

import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;

import org.mql.dao.MemberRepository;
import org.mql.dao.AdmissionRepository;
import org.mql.email.DemandeAdmission;
import org.mql.models.Formation;
import org.mql.models.Member;
import org.mql.models.Role;
import org.mql.services.MemberService;
import org.mql.services.RoleService;
import org.mql.models.Admission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
public class AdmissionController {
	@Autowired
	private AdmissionRepository admissionRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private DemandeAdmission mail;

	@Autowired
	MemberService memberService;

	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/admission")
	public String FormulaireAdmission(Model model, Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		model.addAttribute("member", member);
		model.addAttribute("admission", new Admission());
		return "admission/addAdmission";
	}

	// creation de l'email qui sera envoyee a l'email de l'administrateur
	@PostMapping(value = "/saveAdmission")
	public String sendAdmission(Model model, Admission admission, Member member) throws MessagingException {
		member = memberRepository.findByEmail(member.getEmail());
		admission.setMember(member);
		admissionRepository.save(admission); // TEST if there is possiblity to delete
		memberRepository.save(member);
		String htmlContent = "<body>\r\n" + "<div align=\"center\" style=\"background-color:lightblue\">\r\n"
				+ " <h3>demande d'admission</h3>" + " <p>nom :" + member.getFirstName() + "</p>\r\n" + " <p>prenom :"
				+ member.getLastName() + "</p>\r\n" + " <p>email :" + member.getEmail() + "</p>\r\n"
				+ " <p> mon domaine :" + admission.getDomaine() + "</p>\r\n" + " <p> mes motivations :"
				+ admission.getMotivation() + "</label></p>\r\n"
				+ "<a href=\"http://localhost:8080//messages\">cliquer ici </a>" + "</div>" + "</body>\r\n" + "</html>";
		mail.send("master.qualite.logiciel2019@gmail.com", member.getEmail(), "demande d'admission", htmlContent);
		return "redirect:/dashboard/";
	}

	@GetMapping(value = "/dashboard/demands")
	public String index(Model model) {
		List<Admission> Teachers = admissionRepository.findAll();
		model.addAttribute("listeTeachers", Teachers);
		return "admission/demandes";
	}

	@PostMapping(value = "/acceptation")
	public String accepter(Model model, @RequestParam("id") int id) throws MessagingException {
		Admission teacher = admissionRepository.findById(id).get();
		admissionRepository.deleteById(id);
		Member member = teacher.getMember();
		Role role = roleService.findRoleByName(RoleService.TEACHER);
		member.addRole(role);
		memberRepository.save(member);
		String htmlContent = "<body>\r\n" + "<div align=\"center\" style=\"background-color:lightblue\">\r\n"
				+ " <h3>demande d'admission acceptee </h3>" + " <p> Bonjour, " + member.getFirstName() + " "
				+ member.getLastName()
				+ " votre demande d'etre enseignant dans notre platforme est acceptee par l'administrateur, "
				+ "consulter votre profil comme etant enseignat : </p>" + "<a href=>cliquer ici </a>" + "</body>\r\n"
				+ "</html>";
		mail.send(member.getEmail(), "master.qualite.logiciel2019@gmail.com", "demande d'admission acceptee",
				htmlContent);
		return "redirect:/dashboard/demandes";
	}

	@PostMapping(value = "/refus")
	public String refuser(Model model, @RequestParam("id") int id) throws MessagingException {
		Admission teacher = admissionRepository.findById(id).get();
		Member member = teacher.getMember();
		admissionRepository.deleteById(id);
		String htmlContent = "<body>\r\n" + "<div align=\"center\" style=\"background-color:lightblue\">\r\n"
				+ " <h3>demande d'admission refusee </h3>" + " <p> Bonjour, " + member.getFirstName() + " "
				+ member.getLastName()
				+ " votre demande d'etre enseignant dans notre platforme est refusee par l'administrateur, "
				+ "pour une raison d'administration. pour consulter votre compte :  </p>" + "<a href='localhost:8080/login'>cliquer ici </a>"
				+ "</body>\r\n" + "</html>";
		mail.send(member.getEmail(), "master.qualite.logiciel2019@gmail.com", "demande d'admission refusee",
				htmlContent);

		return "redirect:/dashboard/demandes";
	}

}
