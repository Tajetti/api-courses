package br.com.tajetti.gestao_cursos.modules.Model.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;


public interface RepositoryCurso extends JpaRepository<Curso, UUID>{
    Optional<Curso> findByName(String name);
    
    List<Curso> findByNameContainingIgnoreCase(String name);
    List<Curso> findByCategoryContainingIgnoreCase(String category);
    List<Curso> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);

    @Override
    Optional<Curso> findById(UUID id);
}
