package br.com.fiap.savefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.savefood.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/register")
    public String save( Product product ) {
        return "product/form";
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
