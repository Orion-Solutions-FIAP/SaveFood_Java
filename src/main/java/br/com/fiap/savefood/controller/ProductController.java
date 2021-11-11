package br.com.fiap.savefood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String products(@RequestParam("id") User userLogged, Model model) {
		List<Product> products = productRepository.findByUserAndStatusOrderByExpirationDateAsc(userLogged, ProductStatus.DISPONIVEL);
		model.addAttribute("products", products);
		
        return "product/listAll";
    }
	
	
	
}
