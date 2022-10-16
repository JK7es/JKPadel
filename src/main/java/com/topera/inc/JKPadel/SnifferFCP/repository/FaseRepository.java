package com.topera.inc.JKPadel.SnifferFCP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Fase;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Integer>{
	
}
