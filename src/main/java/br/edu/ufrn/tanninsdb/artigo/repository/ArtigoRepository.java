package br.edu.ufrn.tanninsdb.artigo.repository;

import br.edu.ufrn.tanninsdb.artigo.model.Artigo;
import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByAutor(Usuario autor);

    List<Artigo> findByStatus(Enum<?> status); // vamos melhorar isso depois
}