package com.topera.inc.JKPadel.SnifferFCP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
	

}
