package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jornada;
import com.topera.inc.JKPadel.SnifferFCP.repository.JornadaRepository;

public class JornadaDAOImpl implements JornadaDAO{
	
	private JornadaRepository repository;
	
	@Autowired
	public JornadaDAOImpl(JornadaRepository repository) {
		this.repository = repository;
	}
	

	@Override
	public Jornada save(Jornada jornada) {
		return repository.save(jornada);
	}

	@Override
	public void save(List<Jornada> jornadas) {
		repository.saveAll(jornadas);
		
	}

	@Override
	public void delete(Jornada jornada) {
		repository.delete(jornada);
		
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public Optional<Jornada> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Jornada> findAll() {		
		return repository.findAll();
	}


	@Override
	public Jornada findByPrimary(Integer idLiga, Integer idGrupo, Integer idFase, Integer IdLocal,
								 Integer IdVisitante) {

		return repository.findByPrimary(idLiga, idGrupo, idFase, IdLocal, IdVisitante);
	}
}
