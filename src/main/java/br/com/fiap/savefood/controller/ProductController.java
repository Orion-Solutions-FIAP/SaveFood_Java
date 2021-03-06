package br.com.fiap.savefood.controller;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/update")
	public String updateProductsStatus(Authentication auth) {
		User user = (User) auth.getPrincipal();
		List<Product> allProducts = p.findByUserAndStatus(user, ProductStatus.DISPONIVEL);
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		for (Product product : allProducts) {
			if(product.getExpirationDate().before(today)) {
				product.setStatus(ProductStatus.VENCIDO);
				p.save(product);
			}
		}
		return "redirect:/product";
	}
	
	@GetMapping("/expired")
    public ModelAndView listExpiredProducts(@RequestParam(defaultValue="0") int page, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Page<Product> products = p.findByUserAndStatusOrderByExpirationDateDesc(user, ProductStatus.VENCIDO, PageRequest.of(page, 5));
		ModelAndView modelAndView = new ModelAndView("product/listInvalid");
		modelAndView.addObject("products", products);
		
        return modelAndView;
	}
	
	@GetMapping("/register")
    public String save( Product product ) {
        return "product/form";
    }
	
	@PostMapping("/register")
	public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect, Authentication auth, Model model) {
		if (result.hasErrors()) {
			return "product/form";
		}
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		if(product.getExpirationDate().before(today)) {
			model.addAttribute("msg","N??o ?? poss??vel cadastrar um produto vencido!");
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
    public ModelAndView update(@PathVariable Long id) {
		Optional<Product> optional = p.findById(id);
		Product product = optional.get();
		ModelAndView modelAndView = new ModelAndView("product/updateForm");
		modelAndView.addObject("product", product);
        return modelAndView;
    }
	
	@PostMapping("/update")
	public String update(@Valid Product product, BindingResult result, RedirectAttributes redirect, Authentication auth, Model model) {
		if (result.hasErrors()) {
			return "product/updateForm";
		}
		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		if(product.getExpirationDate().before(today)) {
			model.addAttribute("msg","N??o ?? poss??vel cadastrar um produto vencido!");
			return "product/form";
		}
		
		product.setStatus(ProductStatus.DISPONIVEL);
		User usuario = (User) auth.getPrincipal();
		product.setUser(usuario);
		p.save(product);
		redirect.addFlashAttribute("msg", "Atualizado!!!");
		return "redirect:/product"; 
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		Optional<Product> optional = p.findById(id);
		Product product = optional.get();
		p.delete(product);
		redirect.addFlashAttribute("msg", "Deletado!!!");
		return "redirect:/product";
	}
	
	@GetMapping("/consume/{id}")
	public String consume(@PathVariable Long id) {
		Optional<Product> product = p.findById(id);
		if(product.isPresent()) {
			product.get().setStatus(ProductStatus.CONSUMIDO);
			p.save(product.get());
		}
		return "redirect:/product";
		
	}
	
}
