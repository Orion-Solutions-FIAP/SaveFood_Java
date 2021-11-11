package br.com.fiap.savefood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.savefood.model.Product;
import br.com.fiap.savefood.model.ProductStatus;
import br.com.fiap.savefood.model.User;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findByStatus(ProductStatus status);
	
	public List<Product> findByUser(User user);
	
	public List<Product> findByUserAndStatus(User user, ProductStatus status);
	
	public List<Product> findByUserAndStatusOrderByExpirationDateAsc(User user, ProductStatus status);

}
