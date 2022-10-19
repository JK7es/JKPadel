package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
	
	
	@Query(nativeQuery = true, value = 
		  " SELECT e.* "
		+ "	FROM equipos e, ligas l "
		+ " WHERE e.id_liga = l.id_liga "
		+ " AND l.temporada = :anno " )
	List<Equipo> findByAnno(
			@Param("anno") Integer anno);
}
