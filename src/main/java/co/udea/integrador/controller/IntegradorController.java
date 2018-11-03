package co.udea.integrador.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.udea.integrador.exception.DataNotFoundException;
import co.udea.integrador.model.Proyecto;
import co.udea.integrador.service.ProyectoService;
import co.udea.integrador.util.Messages;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/integrador")
public class IntegradorController {
	
	private static Logger log = LoggerFactory.getLogger(IntegradorController.class);
	
	private ProyectoService proyectoService;
	
	@Autowired
    private Messages messages;	
	
	public IntegradorController(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proyecto encontrado", response = Proyecto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Proyecto> getProyecto( @PathVariable("id") int id){
		 log.debug("REST request getProyecto id : {}", id);
		return ResponseEntity.ok(proyectoService.getProyecto(id));
	}
	
	@GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los proyectos fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Proyecto>> getHeros(){
		log.debug("REST request listar todos los proyectos");
		return ResponseEntity.ok(proyectoService.getProyectos());		
	}
	
	@RequestMapping(value = "borrar", method=RequestMethod.DELETE)
    @ApiOperation(value = "Eliminar proyecto", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proyecto eliminado", response = Proyecto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteProyecto(@RequestBody Proyecto proyecto){
		log.debug("REST request deleteProyecto id : {}", proyecto.getId());
		proyectoService.deleteProyecto(proyecto);
	}
	
	@DeleteMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Borrar proyecto por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proyecto Borrado", response = Proyecto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteProyectoId( @PathVariable("id") int id){
		 log.debug("REST request deleteProyecto id : {}", id);
		 proyectoService.deleteProyectoId(id);
	}
	
	@PutMapping(value = "actualizar/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Actualizar Proyecto", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Proyecto actualizado", response = Proyecto.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void updateHero(@RequestBody Proyecto proyecto ){
		 log.debug("REST request updateProyecto id : {}", proyecto.getId());
		proyectoService.updateProyecto(proyecto);
	}
	
	@RequestMapping(value="crear", method=RequestMethod.POST)
	public Proyecto addHero(@RequestBody Proyecto proyecto) throws DataNotFoundException{
		log.debug("Entro a crear");
		if(proyecto == null){
			throw new DataNotFoundException(messages.get("exception.data_not_found.proyecto"));
		}
		return proyectoService.addProyecto(proyecto);		
		
	}

}
