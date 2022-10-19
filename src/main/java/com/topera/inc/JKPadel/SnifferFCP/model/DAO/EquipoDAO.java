package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;

public interface EquipoDAO {

	Equipo save (Equipo equipo);
	
	void save (List<Equipo> equipos);
	
	void delete (Equipo equipo);
	
	void deleteById (Integer id);
	
	Optional<Equipo> findById(Integer id);
	
	List<Equipo> findAll();
	
	List<Equipo> findByAnno(Integer anno);
}
