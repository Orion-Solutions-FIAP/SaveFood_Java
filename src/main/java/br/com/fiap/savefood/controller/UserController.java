package br.com.fiap.savefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.savefood.model.User;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
    public String save( User user ) {
        return "user/form";
    }
	
	@GetMapping("/login")
    public String login() {
        return "user/login";
    }


}
