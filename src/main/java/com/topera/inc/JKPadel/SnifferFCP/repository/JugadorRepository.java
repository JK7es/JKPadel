package com.topera.inc.JKPadel.SnifferFCP.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ "	FROM jugadores "
		+ " WHERE genero = :genero "
		)
	List<Jugador> findByGenero (
		@Param("genero") String genero);
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ "	FROM jugadores "
		+ " WHERE nombre || ' ' || apellido1 || ' ' || apellido2 LIKE '%:nombreCompleto%'"
		)
	List<Jugador> findByNombreCompleto (
		@Param("nombreCompleto") String nombreCompleto);
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ "	FROM jugadores "
		+ " WHERE nombre LIKE '%:nombre%'"
		)
	List<Jugador> findByNombre (
		@Param("nombre") String nombre);
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ "	FROM jugadores "
		+ " WHERE apellido1 || ' ' || apellido2 LIKE '%:apellidos%'"
		)
	List<Jugador> findByApellidos (
		@Param("apellidos") String apellidos);
	
	@Modifying
	@Query(nativeQuery = true, value = 
		  " UPDATE jugadores "
		+ " SET nombre = :nombre, "
		+ "     apellido1 = :apellido1, "
		+ "     apellido2 = :apellido2, "
		+ "     genero = :genero "
		+ " WHERE id_jugador = :id_jugador "
		)
	Integer update(
		@Param("nombre") String nombre,
		@Param("apellido1") String apellido1,
		@Param("apellido2") String apellido2,
		@Param("genero") String genero,
		@Param("id_jugador") Integer id_jugador);
	
	@Query(nativeQuery = true, value = 
		  " SELECT * "
		+ " FROM jugadores "
		+ " WHERE 1 = 1 "
		+ " AND TRIM(nombre) LIKE :nombre "
		+ " AND TRIM(apellido1) LIKE :apellido1 "
		+ " AND TRIM(apellido2) LIKE :apellido2 "
		)
	List<Jugador> findByName (
		@Param("nombre") String nombre,
		@Param("apellido1") String apellido1,
		@Param("apellido2") String apellido2);
	
	
	
}
