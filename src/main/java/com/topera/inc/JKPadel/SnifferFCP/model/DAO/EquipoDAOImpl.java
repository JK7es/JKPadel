package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;
import com.topera.inc.JKPadel.SnifferFCP.repository.EquipoRepository;

@Component
public class EquipoDAOImpl implements EquipoDAO{

	private EquipoRepository repository;
	
	@Autowired
	public EquipoDAOImpl(EquipoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Equipo save(Equipo equipo) {
		return repository.save(equipo);		
	}

	@Override
	public void save(List<Equipo> equipos) {
		repository.saveAll(equipos);		
	}

	@Override
	public void delete(Equipo equipo) {
		repository.delete(equipo);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Equipo> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Equipo> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Equipo> findByAnno(Integer anno) {
		return repository.findByAnno(anno);				
	}
	
	

}
