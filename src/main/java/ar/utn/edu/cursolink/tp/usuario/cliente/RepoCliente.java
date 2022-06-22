package ar.utn.edu.cursolink.tp.usuario.cliente;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="clientes", excerptProjection = ClienteDTO.class)
public interface RepoCliente extends PagingAndSortingRepository<Cliente, Integer> {
	
	Optional<Cliente> findByNombre(String nombre);
	Page<Cliente> findAll(Pageable page); 
}
