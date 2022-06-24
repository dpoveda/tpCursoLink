package ar.utn.edu.cursolink.tp.promocion;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="promosMembresia", excerptProjection = PromoMembresiaDTO.class)
public interface RepoMembresia extends PagingAndSortingRepository<PromoMembresia, Integer> {
	
	Page<PromoMembresia> findAll(Pageable page); 
	

}
