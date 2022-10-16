package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoJugador;
import com.topera.inc.JKPadel.SnifferFCP.repository.EquipoJugadorRepository;

@Component
public class EquipoJugadorDAOImpl  implements EquipoJugadorDAO{
	
	private EquipoJugadorRepository repository;

	@Autowired
	public EquipoJugadorDAOImpl(EquipoJugadorRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public EquipoJugador save(EquipoJugador equipoJugador) {
		return repository.save(equipoJugador);	
	}

	@Override
	public void save(List<EquipoJugador> equipoJugadores) {
		repository.saveAll(equipoJugadores);			
	}

	@Override
	public void delete(EquipoJugador equipoJugador) {
		repository.delete(equipoJugador);		
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);	
	}

	@Override
	public Optional<EquipoJugador> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<EquipoJugador> findAll() {
		return repository.findAll();
	}

	@Override
	public List<EquipoJugador> findByEquipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoJugador> findByJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoJugador> findByEquipoLiga() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoJugador> findByJugadorLIga() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EquipoJugador> findByAllIds(Integer idJugador, Integer idEquipo, Integer idLiga){
		return repository.findByAllIds(idJugador, idEquipo, idLiga);
	}

	

}
