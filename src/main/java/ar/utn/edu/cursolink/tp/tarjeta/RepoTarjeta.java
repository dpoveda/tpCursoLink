package ar.utn.edu.cursolink.tp.tarjeta;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="tarjetas", excerptProjection = TarjetaDTO.class)
public interface RepoTarjeta extends PagingAndSortingRepository<Tarjeta, Integer> {
	
	
	Optional<Tarjeta> findByNombreBanco(String nombreBanco);
	
	Page<Tarjeta> findAll(Pageable page);
}
