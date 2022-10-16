package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Fase;
import com.topera.inc.JKPadel.SnifferFCP.repository.FaseRepository;


@Component
public class FaseDAOImpl implements FaseDAO{

	private FaseRepository repository;

	@Autowired
	public FaseDAOImpl (FaseRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Fase fase) {
		repository.save(fase);	
	}

	@Override
	public void save(List<Fase> fases) {
		repository.saveAll(fases);	
	}

	@Override
	public void delete(Fase fase) {
		repository.delete(fase);	
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Fase> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Fase> findAll() {
		return repository.findAll();
	}
	
	

}
