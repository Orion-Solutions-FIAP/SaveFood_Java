package br.com.fiap.savefood.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.savefood.model.Product;
import br.com.fiap.savefood.model.ProductStatus;
import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.ProductRepository;
import br.com.fiap.savefood.repository.UserRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private UserRepository u;
	
	@Autowired
	private ProductRepository p;
	
	
	@GetMapping("/register")
    public String save( Product product ) {
        return "product/form";
    }
	
	@PostMapping("/register")
	public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "product/form";
		}
		product.setStatus(ProductStatus.DISPONIVEL);
		Optional<User> optional = u.findById((long) 1);
		User usuario = optional.get();
		product.setUser(usuario);
		p.save(product);
		redirect.addFlashAttribute("msg", "Cadastrado!!!");
		return "redirect:";
	}
	
	@GetMapping("/update")
    public String update( Product product ) {
        return "product/updateForm";
    }
	
	@GetMapping()
    public String products() {
        return "product/listAll";
    }
	
	
	
}
