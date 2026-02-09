package com.alura.challenge.foro_hub.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    //Error de integridad de datos(ejemplo: llaves foráneas que no existen)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErrorDeIntegridad(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Para errores de base de datos generales (como duplicados a nivel SQL)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErrorDeBaseDeDatos(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body("Error de integridad: el recurso ya existe");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarError400LecturaJson (HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("El cuerpo de la solicitud JSON tiene un formato inválido");
    }
}
