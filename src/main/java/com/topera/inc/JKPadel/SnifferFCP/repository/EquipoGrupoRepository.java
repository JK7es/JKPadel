package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoGrupo;


@Repository
public interface EquipoGrupoRepository extends JpaRepository<EquipoGrupo, Integer>{

	
	@Query(nativeQuery = true, value = 
			  " SELECT * "
			+ "	FROM eq_grupo "
			+ " WHERE id_equipo = :idEquipo "
			+ " AND id_grupo = :idGrupo "
			)
		Optional<EquipoGrupo> findByIdEquipoGrupo(
			@Param("idEquipo") Integer idEquipo, 
			@Param("idGrupo") Integer idGrupo);
	
}
