package ar.utn.edu.cursolink.tp.descuento.fijo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="descuentosFijo", excerptProjection = DescuentoFijoDTO.class)
public interface RepoDescuentoFijo extends PagingAndSortingRepository<DescuentoFijo, Integer> {
	
	Page<DescuentoFijo> findAll(Pageable page); 
}


