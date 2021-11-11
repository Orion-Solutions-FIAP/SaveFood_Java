package br.com.fiap.savefood.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.UserRepository;
import br.com.fiap.savefood.service.AuthenticationService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
    public String save( User user ) {
        return "user/form";
    }
	
	@PostMapping
    public String save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "user/form";
        }
        user.setPassword(AuthenticationService.getPasswordEnconder().encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getPassword());
        redirect.addFlashAttribute("msg", "Cadastrado!!!");
        return "redirect:/login";
        
    }


}
