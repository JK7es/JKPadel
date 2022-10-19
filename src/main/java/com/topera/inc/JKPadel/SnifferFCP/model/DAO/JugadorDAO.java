package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;

/**
 * 
 * @author Josete
 *
 * DAO para la entidad Jugadores
 */

public interface JugadorDAO {

	void save (Jugador jugador);
	
	void save (List<Jugador> jugadores);
	
	void delete (Jugador jugador);
	
	void deleteById (Integer id);
	
	Optional<Jugador> findById(Integer id);
	
	List<Jugador> findAll();

	List<Jugador> findByGenero(String genero);

	List<Jugador> findByNombreCompleto(String nombre);

	List<Jugador> findByNombre(String nombre);

	List<Jugador> findByApellidos(String apellidos);

	void update(Jugador jugador);
	
	List<Jugador> findByName(String nombre, String apellido1, String apellido2);
}
