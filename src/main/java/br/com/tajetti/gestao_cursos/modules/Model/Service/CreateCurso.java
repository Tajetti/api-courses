package br.com.tajetti.gestao_cursos.modules.Model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tajetti.gestao_cursos.modules.Exception.CursoEncontradoException;
import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;
import br.com.tajetti.gestao_cursos.modules.Model.Repository.RepositoryCurso;

@Service
public class CreateCurso {
    
    @Autowired
    private RepositoryCurso repository;

    public Curso execute(Curso curso)
    {
       this.repository
            .findByName(curso.getName())
            .ifPresent((cur) -> {
                throw new CursoEncontradoException("O curso já existe!");
            });;
            
        return this.repository.save(curso);
    }
}
