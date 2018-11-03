package co.udea.integrador.service;

import java.util.List;

import co.udea.integrador.model.Proyecto;

public interface ProyectoService {
	
	public List<Proyecto> getProyectos();
	
	public Proyecto getProyecto(int id) ;
	public Proyecto addProyecto(Proyecto proyecto) ;
	public Proyecto updateProyecto(Proyecto proyecto) ;
	public void deleteProyecto(Proyecto proyecto) ;
	public void deleteProyectoId(int id) ;
	

}
