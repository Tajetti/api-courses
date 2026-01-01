package br.com.tajetti.gestao_cursos.modules.Exception;

public class CursoNaoEncontradoException extends RuntimeException {
    public CursoNaoEncontradoException(String message) {
        super(message);
    }
}
