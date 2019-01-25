package org.mql.controllers.dashboard;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/principal")
public class PrincipalTesting {

	@GetMapping("/")
	public @ResponseBody String mainPage(Principal principal) {
		if(principal == null) {

		}
		return principal.toString();
	}
}
