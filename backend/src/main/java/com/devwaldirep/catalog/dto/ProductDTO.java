package com.devwaldirep.catalog.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devwaldirep.catalog.entities.Category;
import com.devwaldirep.catalog.entities.Product;

public class ProductDTO {
	
	

	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private String imgUrl;

	private Instant date;
	
	// Vai permitir selecionar mais de uma categoria para um produto
	private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
	
	
	
	
	public ProductDTO() {
		
	}


	/**
	 * NÃO SE COLOCA COLEÇÃO(LIST) EM CONSTRUTOR
	 */
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	
	
	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	
	/*
	 *  Construtor que recebe a entidade Produto e as categorias.
	 *  Esse Contrutor instancia o ProductDTO colocando as categorias aqui -> private List<CategoryDTO> categories = new ArrayList<CategoryDTO>()
	 */
	public ProductDTO(Product entity, Set<Category> categories ) {
		this(entity); // Chama o construtor que recebe so a Entity(O construtor acima)
		
		/*
		 *  categories -> argumento do construtor, para cada cat que chegou no argumento insere ele na lista de categories transformado em DTO
		 *  this.categories -> List da classe
		 */
		categories.forEach(cat ->  this.categories.add(new CategoryDTO(cat)));
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


	public List<CategoryDTO> getCategories() {
		return categories;
	}


	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
