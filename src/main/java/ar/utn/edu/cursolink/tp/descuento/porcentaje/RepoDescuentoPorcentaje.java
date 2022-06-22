package ar.utn.edu.cursolink.tp.descuento.porcentaje;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="descuentosPorcentaje", excerptProjection = DescuentoPorcentajeDTO.class)
public interface RepoDescuentoPorcentaje extends PagingAndSortingRepository<DescuentoPorcentaje, Integer> {
	
	Page<DescuentoPorcentaje> findAll(Pageable page); 
}


