package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Integer>{
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ "	FROM ligas "
		+ " WHERE temporada = :temporada "
		)
	List<Liga> findByTemporada (
		@Param("temporada") Integer temporada);
	
}
