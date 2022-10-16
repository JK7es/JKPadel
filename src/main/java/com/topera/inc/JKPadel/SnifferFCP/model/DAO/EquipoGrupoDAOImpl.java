package com.topera.inc.JKPadel.SnifferFCP.model.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoGrupo;
import com.topera.inc.JKPadel.SnifferFCP.repository.EquipoGrupoRepository;

@Component
public class EquipoGrupoDAOImpl implements EquipoGrupoDAO{

	private EquipoGrupoRepository repository;
	
	@Autowired
	public EquipoGrupoDAOImpl(EquipoGrupoRepository repository) {
		this.repository = repository;
	}

	@Override
	public EquipoGrupo save(EquipoGrupo equipoGrupo) {
		return repository.save(equipoGrupo);		
	}

	@Override
	public void save(List<EquipoGrupo> equipoGrupos) {
		repository.saveAll(equipoGrupos);		
	}

	@Override
	public void delete(EquipoGrupo equipoGrupo) {
		repository.delete(equipoGrupo);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<EquipoGrupo> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<EquipoGrupo> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<EquipoGrupo> findByIdEquipoGrupo(Integer idEquipo, Integer idGrupo) {
		return repository.findByIdEquipoGrupo(idEquipo, idGrupo);
	}


}
