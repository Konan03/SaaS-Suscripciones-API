package com.manuel.suscripciones.controllers;

import com.manuel.suscripciones.entities.Usuario;
import com.manuel.suscripciones.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;


import java.util.List;

@Validated
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear un usuario (POST /api/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody @Valid Usuario usuario) {
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }


    // Listar todos los usuarios (GET /api/usuarios)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // Buscar usuario por id (GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable @Min(1) Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario por id (PUT /api/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable @Min(1) Long id, @RequestBody @Valid Usuario datosActualizados) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(datosActualizados.getNombre());
                    usuarioExistente.setEmail(datosActualizados.getEmail());
                    usuarioExistente.setPassword(datosActualizados.getPassword());
                    Usuario actualizado = usuarioService.crearUsuario(usuarioExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario por id (DELETE /api/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable @Min(1) Long id) {
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
