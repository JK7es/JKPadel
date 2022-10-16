package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.repository.LigaRepository;

@Component
public class LigaDAOImpl implements LigaDAO{

	private LigaRepository repository;

	@Autowired
	public LigaDAOImpl (LigaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(Liga liga) {
		repository.save(liga);		
	}

	@Override
	public void save(List<Liga> ligas) {
		repository.saveAll(ligas);		
	}

	@Override
	public void delete(Liga liga) {
		repository.delete(liga);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Liga> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Liga> findByCategoria(String categoria) {
		return repository.findByCategoria(categoria);
	}

	@Override
	public List<Liga> findAll() {
		return repository.findAll();
	}

}
