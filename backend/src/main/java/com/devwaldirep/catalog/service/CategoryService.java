package com.devwaldirep.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devwaldirep.catalog.dto.CategoryDTO;
import com.devwaldirep.catalog.entities.Category;
import com.devwaldirep.catalog.repositories.CategoryRepository;

@Service // Registra a classe como um componente que participa do sistema de injeção de dependencia gerenciada pela spring
public class CategoryService {

	
	@Autowired
	private CategoryRepository repository;
	
	
	/**
	 * @Transactional -> garante a integridade da transação com o banco de dados
	 * readOnly = true -> Evita o locking no banco de dados(INDICADO COLOCAR EM OPERAÇÕES SO DE LEITURA), o banco de dados não precisar ficar travado para ler os dados(MELHORA A PERFORMACE)
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		
		List<Category> list = repository.findAll(); // Armazena a lista de categoria do banco de dados
		
		// ---------------- METODO DE CONVERSÃO DE LISTA 1 expressão LAMBDA -----------------------------
		
		/**
		 * Convertento a lista acima para stream()
		 * map() -> Função que converte cada elemento da lista original em outra coisa
		 * Transformando o elemento X que e do tipo Categoty em um novo CategoryDTO(x)
		 * collect(Collectors.toList()) -> Transformando de stream para list(CategotyDTO) novamente
		 */
		//List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		// ---------------- METODO DE CONVERSÃO DE LISTA 2 --------------------------
		
		/*List<CategoryDTO> listDto = new ArrayList<>(); // Criando uma lista de categoriaDTO
		for (Category category : list) { // Varrendo a lista de categoria
			listDto.add(new CategoryDTO(category)); // Armazenando cada elemento da lista de categoria dentro da lista de categoriaDTO
		}
		return listDto; */
		
	}

}
