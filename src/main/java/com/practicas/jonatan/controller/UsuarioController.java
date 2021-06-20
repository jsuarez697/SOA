package com.practicas.jonatan.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.jonatan.request.RequestUsuario;
import com.practicas.jonatan.response.ResponseUsuario;
import com.practicas.jonatan.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {
	
	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/login")
	public ResponseEntity<ResponseUsuario> getResponseUsuario(
			@RequestHeader(value = "X-ClientId", required = false) String clientId,
			@RequestParam(name = "id") @Valid Long id) {
		logger.debug("PRACTICA SOA LOGIN ENDPOINT");
		return new ResponseEntity<>(usuarioService.getUsuario(id), HttpStatus.OK);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<ResponseUsuario>> getlistUsuarios(@RequestParam(name = "country") String country) {
		logger.debug("PRACTICA SOA LIST ENDPOINT");
		return new ResponseEntity<>(usuarioService.getUsuarios(country), HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json")
	public ResponseEntity<?> postUsuario(@Valid @RequestBody RequestUsuario usuario) {
		logger.debug("PRACTICA CREATE ENDPOINT");
		return new ResponseEntity<>(usuarioService.createUsuario(usuario), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(name = "id") Long id) {
		logger.debug("PRACTICA DELETE ENDPOINT");
		return new ResponseEntity<>(usuarioService.deleteUsuario(id), HttpStatus.OK);
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ResponseUsuario> updateUsuario(@Valid @RequestBody RequestUsuario usuario,
			@PathVariable(name = "id") Long id) {
		logger.debug("PRACTICA UPDATE ENDPOINT");
		return new ResponseEntity<>(usuarioService.updateUsuario(usuario, id), HttpStatus.OK);
	}
}
