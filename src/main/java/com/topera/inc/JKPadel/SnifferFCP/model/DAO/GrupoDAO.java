package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;

/**
 * 
 * @author Josete
 *
 * DAO para la entidad Jugadores
 */

public interface GrupoDAO {

	void save (Grupo grupo);
	
	void save (List<Grupo> grupo);
	
	void delete (Grupo grupo);
	
	void deleteById (Integer id);
	
	Optional<Grupo> findById(Integer id);
	
	List<Grupo> findAll();
	
	List<Grupo> findByLiga(Integer id);

}
