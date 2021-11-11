package br.com.fiap.savefood.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dt_expiration")
	private Date expirationDate;
	
	@Column(name = "nr_quantity")
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@ManyToOne
	private User user;

}
