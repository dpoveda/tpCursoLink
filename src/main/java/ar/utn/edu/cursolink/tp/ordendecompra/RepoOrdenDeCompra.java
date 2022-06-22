package ar.utn.edu.cursolink.tp.ordendecompra;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="ordenes", excerptProjection = OrdenDeCompraDTO.class)
public interface RepoOrdenDeCompra extends PagingAndSortingRepository<OrdenDeCompra, Integer> {
	
	Optional<OrdenDeCompra> findByCodigo(String codigo);
	Page<OrdenDeCompra> findAll(Pageable page); 
}
