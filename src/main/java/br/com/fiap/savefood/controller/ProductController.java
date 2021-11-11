package br.com.fiap.savefood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.savefood.model.Product;
import br.com.fiap.savefood.model.ProductStatus;
import br.com.fiap.savefood.model.User;
import br.com.fiap.savefood.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/register")
    public String save( Product product ) {
        return "product/form";
    }
	
	@GetMapping("/update/{id}")
    public String update( Product product ) {
        return "product/updateForm";
    }
	
	@GetMapping("/delete/{id}")
	public String delete(Product product) {
		return "product/updateForm";
	}
	
	@GetMapping()
    public String products(@RequestParam("id") User userLogged, @RequestParam(defaultValue="0") int page, Model model) {
		Page<Product> products = productRepository.findByUserAndStatusOrderByExpirationDateAsc(userLogged, ProductStatus.DISPONIVEL, PageRequest.of(page, 5));
		model.addAttribute("products", products);
		model.addAttribute("user", userLogged);
		
        return "product/listAll";
    }
	
	
	
}
