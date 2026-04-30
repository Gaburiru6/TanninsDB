package br.edu.ufrn.tanninsdb.usuario.repository;

import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByIdOrcid(String idOrcid);
}