package com.devwaldirep.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devwaldirep.catalog.dto.CategoryDTO;
import com.devwaldirep.catalog.dto.ProductDTO;
import com.devwaldirep.catalog.entities.Category;
import com.devwaldirep.catalog.entities.Product;
import com.devwaldirep.catalog.repositories.CategoryRepository;
import com.devwaldirep.catalog.repositories.ProductRepository;
import com.devwaldirep.catalog.service.exceptions.DatabaseException;
import com.devwaldirep.catalog.service.exceptions.ResourceNotFoundException;


@Service // Registra a classe como um componente que participa do sistema de injeção de dependencia gerenciada pela spring
public class ProductService {

	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	/**
	 * @Transactional -> garante a integridade da transação com o banco de dados
	 * readOnly = true -> Evita o locking no banco de dados(INDICADO COLOCAR EM OPERAÇÕES SO DE LEITURA), o banco de dados não precisar ficar travado para ler os dados(MELHORA A PERFORMACE)
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		
		List<Product> list = repository.findAll(); // Armazena a lista de categoria do banco de dados
		
		// ---------------- METODO DE CONVERSÃO DE LISTA 1 expressão LAMBDA -----------------------------
		
		/**
		 * Convertento a lista acima para stream()
		 * map() -> Função que converte cada elemento da lista original em outra coisa
		 * Transformando o elemento X que e do tipo Categoty em um novo ProductDTO(x)
		 * collect(Collectors.toList()) -> Transformando de stream para list(CategotyDTO) novamente
		 */
		//List<ProductDTO> listDto = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
		// ---------------- METODO DE CONVERSÃO DE LISTA 2 --------------------------
		
		/*List<ProductDTO> listDto = new ArrayList<>(); // Criando uma lista de categoriaDTO
		for (Product category : list) { // Varrendo a lista de categoria
			listDto.add(new ProductDTO(category)); // Armazenando cada elemento da lista de categoria dentro da lista de categoriaDTO
		}
		return listDto; */
		
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		
		Page<Product> list = repository.findAll(pageRequest);
		
		// O Page ja e um stream do Java 8 então não é necessario stream() e nem o collect(Collectors.toList())
		
		return list.map(x -> new ProductDTO(x));
		
	}


	@Transactional
	public ProductDTO findById(Long id) {
		
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found")); // Acessando o obj, se não houver obj retorna uma excessão customizada
		
		return new ProductDTO(entity, entity.getCategories()); // utilizando o construtor do ProductDTO que instancia o produto junto com as categorias dele
	}
	


	/**
	 * E feito a copia dos dados do dto para dentro da entidade
	 * @param dto
	 * @return
	 */
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product();
		
		copyDtoToEntity(dto, entity);  // Metodo que copia os dados do ProductDTO para a entidade Product, metodo criado para evitar repetição de codigo nesse metodo e no update
		
		entity = repository.save(entity); // Salvando no BD
		
		return new ProductDTO(entity);  // Salvando a entidade Product em forma de ProductDTO
	}


	/**
	 * Metodo que copia os dados do ProductDTO para a entidade Product
	 * private -> Metodo disponivel apenas para essa classe
	 * @param dto
	 * @param entity
	 */
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear(); // clear() -> Limpa o conjunto(Lista) de categorias que por ventura esteja na entidade, assim garanti que sera copiada somente as categorias que vieram no DTO
		
		for (CategoryDTO catDto : dto.getCategories()) { // Percorrendo a lista de CategoriaDTO
			Category category = categoryRepository.getOne(catDto.getId()); // Instanciando um objeto Category sem tocar no banco de dados com getOne()
			
			entity.getCategories().add(category); // adicionando as categorias na entidade Product
		}
	}



	/**
	 * getOne() -> Metodo necessario sempre que houver uma atualização, ele não acessa o BD
	 * @param id
	 * @param dto
	 * @return
	 */
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		
		try {
			Product entity = repository.getOne(id); // getOne(id) -> Metodo que não utiliza o BD, ele instancia um obj provisorio do Product com o Id informado na memoria
			
			copyDtoToEntity(dto, entity); // Metodo que copia os dados do ProductDTO para a entidade Product, metodo criado para evitar repetição de codigo nesse metodo e no update
			
			entity = repository.save(entity); // Salvando no BD
			
			return new ProductDTO(entity); // Salvando a entidade Product em forma de ProductDTO
			
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
