package practice.cy.alarm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainPage(){
		return "main";
	}

	@GetMapping("/server")
	public String serverPage(){
		return "server";
	}
	
	@GetMapping("/client")
	public String clientPage(){
		return "client";
	}
}
