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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
