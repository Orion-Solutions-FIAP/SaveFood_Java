package br.com.fiap.savefood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
    public String login(User user) {
        return "user/login";
    }
	
	@PostMapping("/login")
	public String login(User user, Model model, BindingResult result, RedirectAttributes redirect) throws Exception {
		
		Optional<User> login = userRepository.findByEmail(user.getEmail());
		User userLogged = login.get();
		
		if(userLogged == null) {
			throw new Exception("User not found");
		}
		
		if(!userLogged.getPassword().equals(user.getPassword())) {
			throw new Exception("Invalid user!");
		}
		
		redirect.addAttribute("id", userLogged);
		
		return "redirect:product";
	}

}
