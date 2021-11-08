package br.com.fiap.savefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.savefood.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
