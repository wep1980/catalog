package com.devwaldirep.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Category> findAll(){
		return repository.findAll();
	}

}
