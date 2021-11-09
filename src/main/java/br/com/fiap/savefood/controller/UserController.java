package br.com.fiap.savefood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping
    public String save( User user ) {
        return "user/form";
    }
	
	@GetMapping("/login")
    public String login() {
        return "user/login";
    }
	
	@PostMapping
    public String save(@ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "user/form";
        }

        userRepository.save(user);
        redirect.addFlashAttribute("msg", "Cadastrado!!!");
        return "redirect:user/login";
        
    }


}
