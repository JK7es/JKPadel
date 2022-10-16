package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;
import com.topera.inc.JKPadel.SnifferFCP.repository.JugadorRepository;


@Component
public class JugadorDAOImpl implements JugadorDAO{

	private JugadorRepository repository;

	@Autowired
	public JugadorDAOImpl (JugadorRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(Jugador jugador) {
		repository.save(jugador);		
	}

	@Override
	public void save(List<Jugador> jugadores) {
		repository.saveAll(jugadores);		
	}

	@Override
	public void delete(Jugador jugador) {
		repository.delete(jugador);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Jugador> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public List<Jugador> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Jugador> findByGenero(String genero) {
		return repository.findByGenero(genero);
	}
	
	@Override
	public List<Jugador> findByNombreCompleto(String nombre) {
		return repository.findByNombreCompleto(nombre);
	}
	
	@Override
	public List<Jugador> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	@Override
	public List<Jugador> findByApellidos(String apellidos) {
		return repository.findByApellidos(apellidos);
	}

	@Override
	public void update(Jugador jugador) {
		repository.update(jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2(), jugador.getGenero(), jugador.getId_jugador());
	}

	@Override
	public Optional<Jugador> findByName(String nombre, String apellido1, String apellido2) {
		return repository.findByName(nombre, apellido1, apellido2);
	}
}
