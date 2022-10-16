package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoJugador;


@Repository
public interface EquipoJugadorRepository extends JpaRepository<EquipoJugador, Integer>{


/*	
	findByEquipo
	findByJugadorLIga
	findByEquipoLiga
	findByJugador
	
	
*/	
	
	@Query(nativeQuery = true, value = 
		  " SELECT 1 "
		+ " FROM eq_jug "
		+ " WHERE 1 = 1"
		+ " AND id_jugador = :idJugador "
		+ " AND id_equipo = :idEquipo "
		+ " AND id_liga = :idLiga "
		)
	List<EquipoJugador> findByAllIds(
		@Param("idJugador") Integer idJugador,
		@Param("idEquipo") Integer idEquipo, 
		@Param("idLiga") Integer idLiga
		);
			
}
