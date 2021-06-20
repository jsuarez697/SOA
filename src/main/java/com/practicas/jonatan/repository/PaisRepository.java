package com.practicas.jonatan.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practicas.jonatan.model.PaisEntity;

@Repository
public interface PaisRepository extends CrudRepository<PaisEntity, Long> {
	
	List<PaisEntity> findByNombrePais(String nombrePais);
}
