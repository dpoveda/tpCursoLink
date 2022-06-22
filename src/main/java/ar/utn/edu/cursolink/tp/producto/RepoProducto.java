package ar.utn.edu.cursolink.tp.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="productos", excerptProjection = ProductoDTO.class)
public interface RepoProducto extends PagingAndSortingRepository<Producto, Integer> {
	
	Optional<Producto> findByNombre(String nombre);
	Page<Producto> findAll(Pageable page); 
}
