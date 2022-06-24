package ar.utn.edu.cursolink.tp.promocion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="promosMedioDePago", excerptProjection = PromoMedioDTO.class)
public interface RepoMedioDePago extends PagingAndSortingRepository<PromoMedioDePago, Integer> {
	
	Page<PromoMedioDePago> findAll(Pageable page); 
	

}
