package com.manuel.suscripciones.repositories;

import com.manuel.suscripciones.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí después podemos agregar consultas personalizadas
    Optional<Usuario> findByEmail(String email);

}
