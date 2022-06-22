package ar.utn.edu.cursolink.tp.carrito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="items", excerptProjection = ItemDTO.class)
public interface RepoItemCarrito extends PagingAndSortingRepository<ItemCarrito, Integer> {
	
	Page<ItemCarrito> findAll(Pageable page); 
	
	
}
