package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;
import com.topera.inc.JKPadel.SnifferFCP.repository.GrupoRepository;


@Component
public class GrupoDAOImpl implements GrupoDAO{

	private GrupoRepository repository;

	@Autowired
	public GrupoDAOImpl (GrupoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(Grupo grupo) {
		repository.save(grupo);		
	}

	@Override
	public void save(List<Grupo> grupos) {
		repository.saveAll(grupos);		
	}

	@Override
	public void delete(Grupo grupos) {
		repository.delete(grupos);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Grupo> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public List<Grupo> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Grupo> findByLiga(Integer id){
		return repository.findByLiga(id);
	}

}
