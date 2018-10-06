package co.udea.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.integrador.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, String>{

	public Optional<Proyecto> findById(int id); 
	
	
}
