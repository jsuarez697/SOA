package com.practicas.jonatan.mapper;

import com.practicas.jonatan.dto.DTOUsuario;
import com.practicas.jonatan.model.PaisEntity;
import com.practicas.jonatan.model.UsuarioEntity;
import com.practicas.jonatan.request.RequestUsuario;

public class UsuarioMapper {

	private UsuarioMapper() {
	}

	public static DTOUsuario mapUsuario(UsuarioEntity usuarioEntity) {
		DTOUsuario dtoUsuario = new DTOUsuario();
		dtoUsuario.setUserId(usuarioEntity.getUserId());
		dtoUsuario.setName(usuarioEntity.getName());
		dtoUsuario.setEmail(usuarioEntity.getEmail());
		dtoUsuario.setPhoneNumber(usuarioEntity.getPhoneNumber());
		return dtoUsuario;
	}

	public static UsuarioEntity mapRequestUsuario(RequestUsuario requestUsuario, UsuarioEntity usuarioEntity) {
		usuarioEntity.setUserId(requestUsuario.getUserId());
		usuarioEntity.setName(requestUsuario.getName());
		usuarioEntity.setEmail(requestUsuario.getEmail());
		usuarioEntity.setPhoneNumber(requestUsuario.getPhoneNumber());
		return usuarioEntity;
	}

	public static UsuarioEntity mapNewUserEntity(RequestUsuario nuevoUsuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUserId(nuevoUsuario.getUserId());
		usuarioEntity.setName(nuevoUsuario.getName());
		usuarioEntity.setEmail(nuevoUsuario.getEmail());
		usuarioEntity.setPhoneNumber(nuevoUsuario.getPhoneNumber());
		usuarioEntity.setPassword(nuevoUsuario.getPassword());
		PaisEntity paisEntity = new PaisEntity();
		paisEntity.addUsuario(usuarioEntity);
		return usuarioEntity;
	}

}
