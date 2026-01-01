package br.com.tajetti.gestao_cursos.modules.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tajetti.gestao_cursos.modules.Model.Entity.Curso;
import br.com.tajetti.gestao_cursos.modules.Model.Service.ServiceCurso;
import jakarta.validation.Valid;




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
    public ResponseEntity<Object> get(@RequestParam(required = false) String name, @RequestParam(required = false) String category) 
    {
        try{
            List<Curso> cursos = this.service.buscar(name, category);
            return ResponseEntity.ok().body(cursos);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable UUID id, @RequestBody Curso curso) {
        try {
            var result = this.service.atualizar(id, curso);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
}
