package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Fase;

/**
 * 
 * @author Josete
 *
 * DAO para la entidad Fase
 */

public interface FaseDAO {

	void save (Fase fase);
	
	void save (List<Fase> fases);
	
	void delete (Fase fase);
	
	void deleteById (Integer id);
	
	Optional<Fase> findById(Integer id);
	
	List<Fase> findAll();

}
