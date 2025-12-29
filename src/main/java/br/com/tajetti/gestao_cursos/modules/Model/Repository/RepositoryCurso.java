package br.com.tajetti.gestao_cursos.modules.Model.Repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;


public interface RepositoryCurso extends JpaRepository<Curso, UUID>{
    Optional<Curso> findByName(String name);
    Optional<Curso> findByNameOrCategory(String name, String category);
}
