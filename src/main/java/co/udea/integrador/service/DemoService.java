package co.udea.integrador.service;

import java.util.List;

import co.udea.integrador.model.Demo;

public interface DemoService {
	
	public List<Demo> getDemos();
	public Demo getDemo(int id) ;

}
