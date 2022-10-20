package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jornada;

public interface JornadaDAO {

	Jornada save (Jornada jornada);
	
	void save (List<Jornada> jornadas);
	
	void delete (Jornada jornada);
	
	void deleteById (Integer id);
	
	Optional<Jornada> findById(Integer id);
	
	List<Jornada> findAll();
	
	Jornada findByPrimary(Integer idLiga, Integer idGrupo, Integer idFase, Integer IdLocal, Integer IdVisitante);
	
}
