package br.com.fiap.savefood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.savefood.model.Product;
import br.com.fiap.savefood.model.ProductStatus;
import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.ProductRepository;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository p;
	
	@GetMapping
    public ModelAndView index(@RequestParam(defaultValue="0") int page, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Page<Product> products = p.findByUserAndStatusOrderByExpirationDateAsc(user, ProductStatus.DISPONIVEL, PageRequest.of(page, 5));
		ModelAndView modelAndView = new ModelAndView("product/listAll");
		modelAndView.addObject("products", products);
		
        return modelAndView;
	}
	
	@GetMapping("/register")
    public String save( Product product ) {
        return "product/form";
    }
	
	@PostMapping("/register")
	public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect, Authentication auth) {
		if (result.hasErrors()) {
			return "product/form";
		}
		product.setStatus(ProductStatus.DISPONIVEL);
		User usuario = (User) auth.getPrincipal();
		product.setUser(usuario);
		p.save(product);
		redirect.addFlashAttribute("msg", "Cadastrado!!!");
		return "redirect:"; 
	}

	@GetMapping("/update/{id}")
    public String update( Product product ) {
        return "product/updateForm";
    }
	
	@GetMapping("/delete/{id}")
	public String delete(Product product) {
		return "product/updateForm";
	}
	
	
	
	
	
}
