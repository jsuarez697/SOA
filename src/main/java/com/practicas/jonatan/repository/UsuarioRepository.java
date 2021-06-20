package com.practicas.jonatan.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practicas.jonatan.model.PaisEntity;
import com.practicas.jonatan.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
	
	List<UsuarioEntity> findByPaisEntity(PaisEntity paisEntity);
	
	List<UsuarioEntity> findByUserId(String userId);
}
