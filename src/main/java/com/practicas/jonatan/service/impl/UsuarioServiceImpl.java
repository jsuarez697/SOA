package com.practicas.jonatan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.jonatan.exception.InvalidArgumentException;
import com.practicas.jonatan.exception.RecordNotFoundException;
import com.practicas.jonatan.mapper.UsuarioMapper;
import com.practicas.jonatan.model.PaisEntity;
import com.practicas.jonatan.model.UsuarioEntity;
import com.practicas.jonatan.repository.PaisRepository;
import com.practicas.jonatan.repository.UsuarioRepository;
import com.practicas.jonatan.request.RequestUsuario;
import com.practicas.jonatan.response.ResponseUsuario;
import com.practicas.jonatan.service.UsuarioService;
import com.practicas.jonatan.utilities.Utilities;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Override
	public ResponseUsuario getUsuario(Long id) {
		ResponseUsuario responseUsuario = new ResponseUsuario();
		Optional<UsuarioEntity> response = Optional.ofNullable(usuarioRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(Utilities.USER_ID + id + Utilities.NOT_FOUND)));
		if (response.isPresent()) {
			responseUsuario = prepareResponseUsuario(response.get());
		}
		return responseUsuario;
	}

	@Override
	public ResponseUsuario createUsuario(RequestUsuario usuario) {
		if (!isNewUser(usuario.getUserId())) {
			throw new InvalidArgumentException("The userId " + "'" + usuario.getUserId() + "'" + " already exists");
		}
		return prepareResponseUsuario(usuarioRepository.save(UsuarioMapper.mapNewUserEntity(usuario)));
	}

	@Override
	public boolean deleteUsuario(Long id) {
		boolean deleted = false;
		Optional<UsuarioEntity> response = Optional.ofNullable(usuarioRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(Utilities.USER_ID + id + Utilities.NOT_FOUND)));
		if (response.isPresent()) {
			usuarioRepository.delete(response.get());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public ResponseUsuario updateUsuario(RequestUsuario newUser, Long id) {
		ResponseUsuario responseUsuario = new ResponseUsuario();
		Optional<UsuarioEntity> response = Optional.ofNullable(usuarioRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(Utilities.USER_ID + id + Utilities.NOT_FOUND)));
		if (response.isPresent()) {
			responseUsuario = prepareResponseUsuario(
					usuarioRepository.save(UsuarioMapper.mapRequestUsuario(newUser, response.get())));
		}
		return responseUsuario;
	}

	@Override
	public List<ResponseUsuario> getUsuarios(String country) {
		List<ResponseUsuario> usuarios = new ArrayList<>();
		List<PaisEntity> paisUsuario = paisRepository.findByNombrePais(country);
		if (paisUsuario.stream().noneMatch(pais -> pais.getNombrePais().equalsIgnoreCase(country))) {
			throw new InvalidArgumentException("country parameter " + "'" + country + "'" + " is not valid");
		}
		List<UsuarioEntity> usuariosEncontrados = usuarioRepository.findByPaisEntity(paisUsuario.get(0));
		for (UsuarioEntity usuario : usuariosEncontrados) {
			usuarios.add(prepareResponseUsuario(usuario));
		}
		return usuarios;
	}

	private boolean isNewUser(String userId) {
		boolean isNew = false;
		List<UsuarioEntity> foundUser = usuarioRepository.findByUserId(userId);
		if (foundUser == null || foundUser.isEmpty()) {
			isNew = true;
		}
		return isNew;
	}

	private ResponseUsuario prepareResponseUsuario(UsuarioEntity usuarioEntity) {
		ResponseUsuario responseUsuario = new ResponseUsuario();
		responseUsuario.setDtoUsuario(UsuarioMapper.mapUsuario(usuarioEntity));
		return responseUsuario;
	}
}
