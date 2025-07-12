package com.manuel.suscripciones.controllers;

import com.manuel.suscripciones.entities.Usuario;
import com.manuel.suscripciones.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear un usuario (POST /api/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }


    // Listar todos los usuarios (GET /api/usuarios)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}
