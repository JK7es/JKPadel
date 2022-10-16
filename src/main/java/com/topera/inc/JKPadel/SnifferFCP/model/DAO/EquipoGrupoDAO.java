package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoGrupo;

public interface EquipoGrupoDAO {

	EquipoGrupo save (EquipoGrupo equipoGrupo);
	
	void save (List<EquipoGrupo> equipoGrupos);
	
	void delete (EquipoGrupo equipoGrupo);
	
	void deleteById (Integer id);
	
	Optional<EquipoGrupo> findById(Integer id);
	
	List<EquipoGrupo> findAll();
	
	Optional<EquipoGrupo> findByIdEquipoGrupo(Integer idEquipo, Integer idGrupo);
}
