package ar.utn.edu.cursolink.tp.promocion;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="promosCupon", excerptProjection = PromoCuponDTO.class)
public interface RepoPromoCupon extends PagingAndSortingRepository<PromoCupon, Integer> {
	
	Optional<PromoCupon> findByCodigo(String codigo);
	Page<PromoCupon> findAll(Pageable page); 
	

}
