package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoJugador;

public interface EquipoJugadorDAO {

	EquipoJugador save (EquipoJugador equipoJugador);
	
	void save (List<EquipoJugador> equipoJugadores);
	
	void delete (EquipoJugador equipoJugador);
	
	void deleteById (Integer id);
	
	Optional<EquipoJugador> findById(Integer id);
	
	List<EquipoJugador> findAll();
	
	List<EquipoJugador> findByEquipo();
	
	List<EquipoJugador> findByJugador();
	
	List<EquipoJugador> findByEquipoLiga();
	
	List<EquipoJugador> findByJugadorLIga();
	
	List<EquipoJugador> findByAllIds(Integer idJugador, Integer idEquipo, Integer idLiga);
	
}
