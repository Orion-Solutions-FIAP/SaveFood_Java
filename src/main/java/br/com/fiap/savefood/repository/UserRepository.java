package br.com.fiap.savefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.savefood.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
