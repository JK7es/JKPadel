package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;

/**
 * 
 * @author Josete
 *
 * DAO para la entidad LIGAS
 */

public interface LigaDAO {

	void save (Liga liga);
	
	void save (List<Liga> ligas);
	
	void delete (Liga liga);
	
	void deleteById (Integer id);
	
	Optional<Liga> findById(Integer id);
	
	List<Liga> findByCategoria (String categoria);
	
	List<Liga> findAll();
}
