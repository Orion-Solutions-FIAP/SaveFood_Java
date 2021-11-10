package br.com.fiap.savefood.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_PRODUCT")
@SequenceGenerator(name = "product", sequenceName = "SQ_T_PRODUCT", allocationSize = 1)
public class Product {
	
	@Id
	@GeneratedValue(generator = "product", strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long id;
	
	@Column(name = "nm_product")
	private String name;
	
	@Column(name = "dt_expiration")
	private LocalDate expirationDate;
	
	@Column(name = "nr_quantity ")
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_status")
	private ProductStatus status;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

}
