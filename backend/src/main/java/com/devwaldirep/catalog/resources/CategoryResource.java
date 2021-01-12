package com.devwaldirep.catalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devwaldirep.catalog.dto.CategoryDTO;
import com.devwaldirep.catalog.service.CategoryService;

/**
* Classe que implementa o controlador REST
* @author wepbi
*
*/
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	
	@Autowired
	private CategoryService service;
	
	/**
	 * ResponseEntity -> encapsula uma resposta HTTP
	 * List e uma interface, não pode ser instanciada, é necessario instancia uma classe que implemente a interface
	 */
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		
		List<CategoryDTO> list = service.findAll();
		
		// Retorna a lista no corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
	}
	
}