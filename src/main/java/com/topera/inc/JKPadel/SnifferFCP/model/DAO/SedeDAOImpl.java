package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Sede;
import com.topera.inc.JKPadel.SnifferFCP.repository.SedeRepository;

@Component
public class SedeDAOImpl implements SedeDAO{

	private SedeRepository repository;
	
	@Autowired
	public SedeDAOImpl(SedeRepository repository) {
		this.repository = repository;
	}

	@Override
	public Sede save(Sede sede) {
		return repository.save(sede);		
	}

	@Override
	public void save(List<Sede> sedes) {
		repository.saveAll(sedes);		
	}

	@Override
	public void delete(Sede sede) {
		repository.delete(sede);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Sede> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Sede> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Sede> findBySede(String sede) {
		return repository.findBySede(sede);
	}

}
