package co.udea.integrador.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.integrador.exception.DataNotFoundException;
import co.udea.integrador.model.Demo;
import co.udea.integrador.repository.DemoRepository;
import co.udea.integrador.service.DemoService;
import co.udea.integrador.util.Messages;

@Service
public class DemoServiceImpl implements DemoService {
	
	private final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	private Messages messages;	
	private DemoRepository demoRepository;

	public DemoServiceImpl(DemoRepository heroRepository, Messages messages) {
		this.demoRepository = heroRepository;
		this.messages = messages;
	}

	@Override
	public List<Demo> getDemos() {
		log.debug("Inicio getDemos");
		List<Demo> heroes= demoRepository.findAll();
		log.debug("Fin getDemos");
		return heroes;
	}
	
	@Override
	public Demo getDemo(int id) {
		log.debug("Inicio getDemo: id = {}", id);
		Optional<Demo> hero = demoRepository.findById(id);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.demo"));
		}
		log.debug("Fin getHero: heroe = {}", hero.get());
		return hero.get();
	}


}
