package br.com.fiap3espb.auto_escola_3espb.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalDeExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarBadRequest(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosBadRequest::new).toList());
    }

    private record DadosBadRequest(String campo, String mensagem) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}