package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer>{
	
	@Query(nativeQuery = true, value = 
			  " SELECT * "
			+ "	FROM sedes "
			+ " WHERE sede = :sede "
			)
		Optional<Sede> findBySede (
			@Param("sede") String sede);
}
