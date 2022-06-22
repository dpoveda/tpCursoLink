package ar.utn.edu.cursolink.tp.carrito;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="carritos", excerptProjection = CarritoDTO.class)
public interface RepoCarrito extends PagingAndSortingRepository<Carrito, Integer> {
	
	Page<Carrito> findAll(Pageable page); 
}
