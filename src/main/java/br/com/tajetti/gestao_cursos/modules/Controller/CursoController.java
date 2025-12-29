package br.com.tajetti.gestao_cursos.modules.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;
import br.com.tajetti.gestao_cursos.modules.Model.Service.CreateCurso;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    private CreateCurso service;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody Curso curso) {
        try{
            var result = this.service.execute(curso);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
