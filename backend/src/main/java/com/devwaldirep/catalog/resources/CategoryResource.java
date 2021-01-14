package com.devwaldirep.catalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	/*@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		
		List<CategoryDTO> list = service.findAll();
		
		// Retorna a lista no corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
	}*/
	
	
	
	
	/**
	 * @RequestParam Parametro opicional de preenchimento na tela, ele configura o metodo.
	 *  @PathVariable -> Parametro de URL, geralmente e um dado obrigatorio
	 * List e uma interface, não pode ser instanciada, é necessario instancia uma classe que implemente a interface
	 */
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<CategoryDTO> list = service.findAllPaged(pageRequest);
		
		// Retorna a lista no corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
	}
	
	
	
	/**
	 * @PathVariable -> Parametro de URL, geralmente e um dado obrigatorio
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		
		CategoryDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	/**
	 * Os dados quando vem da tela, vem na forma de objeto ou seja ele recebe um CategoryDTO e o @RequestBody captura esse objeto que veio na requisição.
	 * 
	 * Quando e feita uma requisição e ela da certo, por padrão o codigo HTTP é 200(Requisição com sucesso), Mas no caso da criação de um novo recurso o 
	 * correto e o codigo HTTP 201(Recurso criado).
	 * 
	 * Sera retornado no metodo o codigo 201 e um parametro adicional no cabeçalho da resposta que sera o endereço desse novo recurso criado.
	 * @param dto
	 * @return
	 */
	@PostMapping // No padrao REST quando e inserido um novo recurso e utilizado o padrão POST
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto){
		
		dto = service.update(id ,dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build(); // Retorna o codigo HTTP 204 avisando que deu certo e no corpo da resposta esta vazio
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}