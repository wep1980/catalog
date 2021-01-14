package com.devwaldirep.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devwaldirep.catalog.dto.CategoryDTO;
import com.devwaldirep.catalog.entities.Category;
import com.devwaldirep.catalog.repositories.CategoryRepository;
import com.devwaldirep.catalog.service.exceptions.DatabaseException;
import com.devwaldirep.catalog.service.exceptions.ResourceNotFoundException;


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


	@Transactional
	public CategoryDTO findById(Long id) {
		
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found")); // Acessando o obj, se não houver obj retorna uma excessão customizada
		
		return new CategoryDTO(entity);
	}
	


	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category();
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}


	
	/**
	 * getOne() -> Metodo necessario sempre que houver uma atualização, ele não acessa o BD
	 * @param id
	 * @param dto
	 * @return
	 */
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		
		try {
			Category entity = repository.getOne(id); // getOne(id) -> Metodo que não utiliza o BD, ele instancia um obj provisorio do Category com o Id informado na memoria
			entity.setName(dto.getName()); // Atualizando os dados que estavam na memoria
			entity = repository.save(entity); // Salvando no BD
			
			return new CategoryDTO(entity);
			
		} catch (EntityNotFoundException e) { // EntityNotFoundException -> excessão para entidade que não existe
			throw new ResourceNotFoundException("Id not found exception" + id);
		}
	}


	
	/**
	 * No metodo de Delete tratar sempre as excessões.
	 * 
	 * No metodo delete não e necessario colocar o @Transactional porque sera capturada uma excessão do BD caso ela ocorra, 
	 * e com o @Transactional não e possivel capturar essa excessão.
	 * @param id
	 */
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) { // EmptyResultDataAccessException -> Excessão caso tente deletar um id que não existe
			throw new ResourceNotFoundException("Id not found");
			
		} catch (DataIntegrityViolationException e) { // DataIntegrityViolationException -> Excessão caso tente deletar uma categoria que contenha produtos
			throw new DatabaseException("Integrity violation");
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
