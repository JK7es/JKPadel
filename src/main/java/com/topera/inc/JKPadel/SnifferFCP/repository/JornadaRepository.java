package com.topera.inc.JKPadel.SnifferFCP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer>{
	
	@Query(nativeQuery = true, value = 
			  " SELECT * "
			+ " FROM jornadas "
			+ " WHERE id_liga = :idLiga "
			+ " AND id_grupo = :idGrupo "
			+ " AND id_fase = : idFase "
			+ " AND id_equipo_local = :idLocal "
			+ " AND id_equipo_visitante = :idVisitante"			
			)
	Jornada findByPrimary(
			@Param("idLiga") Integer idLiga, 
			@Param("idGrupo") Integer idGrupo, 
			@Param("idFase") Integer idFase, 
			@Param("IdLocal") Integer idLocal, 
			@Param("IdVisitante") Integer idVisitante);
}
