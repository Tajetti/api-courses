package br.com.tajetti.gestao_cursos.modules.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;
import br.com.tajetti.gestao_cursos.modules.Model.Service.ServiceCurso;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    private ServiceCurso service;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody Curso curso) {
        try{
            var result = this.service.criar(curso);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> get(@RequestParam(required = false) String name, @RequestParam(required = false) String category
    ) 
    {
        List<Curso> cursos = this.service.buscar(name, category);
        return ResponseEntity.ok().body(cursos);
    }
    
    
}
