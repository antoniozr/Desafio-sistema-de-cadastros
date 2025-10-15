package com.example.desafio_sistema_cadastro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEcontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEcontrado(RecursoNaoEcontradoException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestap", LocalDateTime.now());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        body.put("error", "recurso não encontrado");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<Object> handleOperacaoNaoPermitida(OperacaoNaoPermitidaException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Operação não permitida");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
