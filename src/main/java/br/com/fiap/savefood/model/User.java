package br.com.fiap.savefood.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="T_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_T_USER", allocationSize = 1)
public class User {
	
	@Id
	@GeneratedValue(generator = "user", strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome é obrigatório. Favor digitar o nome.")
	private String name;
	
	@NotBlank(message = "O email é obrigatório. Favor digitar o email.")
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "E-mail não válido. Favor digitar um e-mail válido.")
	private String email;
	
	@NotBlank(message = "A senha é obrigatória. Favor digitar uma senha.")
	private String password;
	
}
