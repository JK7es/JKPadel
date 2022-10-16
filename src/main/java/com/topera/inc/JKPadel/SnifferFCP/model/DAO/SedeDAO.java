package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Sede;

public interface SedeDAO {

	Sede save (Sede sede);
	
	void save (List<Sede> sedes);
	
	void delete (Sede sede);
	
	void deleteById (Integer id);
	
	Optional<Sede> findById(Integer id);
	
	List<Sede> findAll();
	
	Optional<Sede> findBySede (String sede);
}
