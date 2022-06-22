package ar.utn.edu.cursolink.tp.proveedor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="proveedores", excerptProjection = ProveedorDTO.class)
public interface RepoProveedor extends PagingAndSortingRepository<Proveedor, Integer> {
	Optional<Proveedor> findByNombre(String nombre);
	Page<Proveedor> findAll(Pageable page);
}
