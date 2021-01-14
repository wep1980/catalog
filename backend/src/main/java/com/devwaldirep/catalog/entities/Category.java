package com.devwaldirep.catalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L; // Permite que esse objeto seja convertido em bytes
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	
	// Feito para auditoria
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Armazenamento da hora em UTC(sem especificar um timezone)
	private Instant createdAt;
	
	
	// Feito para auditoria
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Armazenamento da hora em UTC(sem especificar um timezone)
	private Instant updateAt;
	
	
	public Category() {
		
	}


	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}


	// Sempre que uma categoria for salva sera armazenado o instante atual
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	// Sempre que uma categoria for atualizada sera armazenado o instante atual
	public Instant getUpdateAt() {
		return updateAt;
	}
	
	
	@PrePersist // Salva antes de ir para o BD
	public void persistPreCreate() {
		
		createdAt = Instant.now();
	}
	
	
	@PreUpdate // Atualiza antes de ir para o BD
    public void persistPreUpdate() {
		
    	updateAt = Instant.now();
	}
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}