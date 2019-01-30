package org.mql.controllers.dashboard;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mql.models.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	Logger logger = LogManager.getLogger();

	
	@GetMapping("/login")
	public String login(Model model,HttpServletRequest request,Principal principal) {
		if(principal!=null) {
			return "redirect:/dashboard/";
		}
		model.addAttribute("member",new Member());
		
		try {
			Object flash = request.getSession().getAttribute("flash");
			model.addAttribute("flash",flash);
			request.getSession().removeAttribute("flash");
			
		}catch(Exception e){
			// flash doesn't exist.. do nothing
			
		}	
		
		return "main_views/login";
		
	}
	
}
