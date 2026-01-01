package br.com.tajetti.gestao_cursos.modules.Model.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tajetti.gestao_cursos.modules.Exception.CursoEncontradoException;
import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;
import br.com.tajetti.gestao_cursos.modules.Model.Repository.RepositoryCurso;

@Service
public class ServiceCurso {
    
    @Autowired
    private RepositoryCurso repository;

    public Curso criar(Curso curso)
    {
       this.repository
            .findByName(curso.getName())
            .ifPresent((cur) -> {
                throw new CursoEncontradoException("O curso já existe!");
            });
            
        return this.repository.save(curso);
    }

    public List<Curso> buscar(String name, String category) {
        if(name != null && category != null) {
            return this.repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category);
        }
        
        if(name != null) {
            return this.repository.findByNameContainingIgnoreCase(name);
        }

        if(category != null) {
            return this.repository.findByCategoryContainingIgnoreCase(category);
        }
       
        return this.repository.findAll();
    }

    public Curso atualizar(UUID id, Curso curso) {
        var existente = this.repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("O curso com o id" + id + " não foi encontrado!");
        });

        if(curso.getName() != null) {
            existente.setName(curso.getName());
        }

        if(curso.getCategory() != null) {
            existente.setCategory(curso.getCategory());
        }

        return this.repository.save(existente);
    }
}