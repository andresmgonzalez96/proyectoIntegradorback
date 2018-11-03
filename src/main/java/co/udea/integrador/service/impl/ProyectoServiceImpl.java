package co.udea.integrador.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.integrador.exception.DataNotFoundException;
import co.udea.integrador.model.Proyecto;
import co.udea.integrador.repository.ProyectoRepository;
import co.udea.integrador.service.ProyectoService;
import co.udea.integrador.util.Messages;


@Service
public class ProyectoServiceImpl implements ProyectoService {
	
	private final Logger log = LoggerFactory.getLogger(ProyectoServiceImpl.class);
	
	private Messages messages;	
	private ProyectoRepository proyectoRepository;

	public ProyectoServiceImpl(ProyectoRepository proyectoRepository, Messages messages) {
		this.proyectoRepository = proyectoRepository;
		this.messages = messages;
	}

	@Override
	public List<Proyecto> getProyectos() {
		log.debug("Inicio getProyectos");
		List<Proyecto> proyectos = proyectoRepository.findAll();
		log.debug("Fin getProyectos");
		return proyectos;
	}
	
	@Override
	public Proyecto getProyecto(int id) {
		log.debug("Inicio getProyecto: id = {}", id);
		Optional<Proyecto> proyecto = proyectoRepository.findById(id);
		if(!proyecto.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getProyecto: proyecto = {}", proyecto.get());
		return proyecto.get();
	}
	
	@Override
	public void deleteProyecto(Proyecto proyecto) {
		proyectoRepository.delete(proyecto);		
	}
	
	@Override
	public Proyecto updateProyecto(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}
	
	@Override
	public Proyecto addProyecto(Proyecto proyecto) {
//		List<Proyecto> proyectos= proyectoRepository.findAll();
//		proyecto.setId(proyectos.size());
		return proyectoRepository.save(proyecto);
	}
	
	@Override
	public void deleteProyectoId(int id) {
		log.debug("Inicio getProyecto: id = {}", id);
		Optional<Proyecto> proyecto = proyectoRepository.findById(id);
		if(!proyecto.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getProyecto: proyecto = {}", proyecto.get());
		proyectoRepository.delete(proyecto.get());		
	}


}
