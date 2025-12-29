package br.com.tajetti.gestao_cursos.modules.Model.Service;

import java.util.List;

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
            });;
            
        return this.repository.save(curso);
    }

    public List<Curso> buscar(String name, String category)
    {
        if(name != null && category != null)
        {
            return this.repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category);
        }

        if(name != null)
        {
            return this.repository.findByNameContainingIgnoreCase(name);
        }

        if(category != null)
        {
            return this.repository.findByCategoryContainingIgnoreCase(category);
        }
       
        return this.repository.findAll();
    }
}