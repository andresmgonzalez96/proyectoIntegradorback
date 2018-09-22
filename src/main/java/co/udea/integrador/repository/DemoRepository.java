package co.udea.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.udea.integrador.model.Demo;

public interface DemoRepository extends JpaRepository<Demo, String>{

	public Optional<Demo> findById(int id); 
	
	
}
