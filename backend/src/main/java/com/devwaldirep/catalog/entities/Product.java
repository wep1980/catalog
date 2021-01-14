package com.devwaldirep.catalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(columnDefinition = "TEXT") // Mapeia no banco de dados não como varchar, mas sim como TEXT, assim aceita valores LONGOS
	private String description;
	
	private Double price;
	
	private String imgUrl;
	
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Armazenamento da data em UTC(sem especificar um timezone)
	private Instant date;
	
	
	/**
	 *  Set -> e uma interface, tipo de conjunto que não aceita repetições.
	 *  @JoinTable -> Faz a associação entre 2 entidades, no caso como e uma relação de muitos para muitos entre as tabelas e gerada uma nova tabela com o Id das 2 tabelas(Product e category)
	 */
	@ManyToMany
	@JoinTable(
			name = "tb_product_category",
	        joinColumns = @JoinColumn(name ="product_id"),
	        inverseJoinColumns = @JoinColumn(name ="category_id")
			)
	Set<Category> categories = new HashSet<>();
	
	
	
	
	// Os FRAMEWORKS ATUALMENTE NECESSITAM DOS CONTRUTORES SEM ARGUMENTOS
	public Product() {
		
	}


	public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}


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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public Instant getDate() {
		return date;
	}


	public void setDate(Instant date) {
		this.date = date;
	}


	public Set<Category> getCategories() {
		return categories;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
