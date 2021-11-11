package br.com.fiap.savefood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.savefood.model.Product;
import br.com.fiap.savefood.model.ProductStatus;
import br.com.fiap.savefood.model.User;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findByUserAndStatusOrderByExpirationDateAsc(User user, ProductStatus status);

}
