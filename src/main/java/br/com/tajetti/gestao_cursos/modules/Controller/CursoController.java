package br.com.tajetti.gestao_cursos.modules.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tajetti.gestao_cursos.modules.Exception.CursoNaoEncontradoException;
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
            return ResponseEntity.status(201).body(result);
        } catch (CursoNaoEncontradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> get(@RequestParam(required = false) String name, @RequestParam(required = false) String category) 
    {
        List<Curso> cursos = this.service.buscar(name, category);
        return ResponseEntity.ok().body(cursos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable UUID id, @RequestBody Curso curso) {
        try {
            var result = this.service.atualizar(id, curso);
            return ResponseEntity.ok().body(result);
        } catch (CursoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            var result = this.service.deletar(id);
            return ResponseEntity.ok().body(result);
        } catch (CursoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> patchActive(@PathVariable UUID id) {
        try {
            var result = this.service.toggle(id);
            return ResponseEntity.ok().body(result);
        } catch (CursoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}