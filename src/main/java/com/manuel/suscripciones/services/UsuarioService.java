package com.manuel.suscripciones.services;

import com.manuel.suscripciones.entities.Usuario;
import com.manuel.suscripciones.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Validar campos vacíos
        if (usuario.getNombre() == null || usuario.getNombre().isBlank() ||
                usuario.getEmail() == null || usuario.getEmail().isBlank() ||
                usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("Nombre, email y contraseña son obligatorios.");
        }

        // Verificar si el email ya está registrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // Guardar el usuario
        return usuarioRepository.save(usuario);
    }

    // Listar todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Eliminar
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
