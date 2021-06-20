package com.practicas.jonatan.service;

import java.util.List;

import com.practicas.jonatan.request.RequestUsuario;
import com.practicas.jonatan.response.ResponseUsuario;

public interface UsuarioService {

	ResponseUsuario getUsuario(Long id);

	ResponseUsuario createUsuario(RequestUsuario usuario);

	boolean deleteUsuario(Long id);

	ResponseUsuario updateUsuario(RequestUsuario usuario, Long id);

	List<ResponseUsuario> getUsuarios(String country);

}
