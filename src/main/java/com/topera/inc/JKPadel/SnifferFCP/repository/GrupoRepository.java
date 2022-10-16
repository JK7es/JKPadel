package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
	
	@Query(nativeQuery = true, value = 
			  " SELECT * "
			+ "	FROM grupos "
			+ " WHERE id_liga = :idLiga "
			)
		List<Grupo> findByLiga (
			@Param("idLiga") Integer idLiga);
}
