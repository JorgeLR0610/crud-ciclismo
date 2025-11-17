package io.github.jorgelr0610.crud_ciclismo.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Captura cuando no se encuentra un registro
    @ExceptionHandler(RuntimeException.class)
    public String handleNotFound(RuntimeException ex, Model model) {
        model.addAttribute("titulo", "Error");
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("volverUrl", "/");
        return "error"; // templates/error.html
    }
    
    // Captura errores de base de datos (duplicados, violación de FK, etc)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDatabaseError(DataIntegrityViolationException ex, Model model) {
        String errorMessage = ex.getMessage();
        String titulo = "Error en la operación";
        String mensajeUsuario = "Ha ocurrido un error al procesar la operación.";
        String tipo = "danger";

        if (errorMessage.contains("Duplicate entry") || errorMessage.contains("unique constraint")) {
            titulo = "Uno de los campos está duplicado";
            tipo = "warning";
        }        
        // Detectar violación de FOREIGN KEY constraint
        else if (errorMessage.contains("foreign key constraint") || 
                 errorMessage.contains("Cannot delete or update a parent row")) {
            titulo = "El registro no se puede eliminar porque tiene relación con otros datos.";
            tipo = "danger";
                 }
        else if (errorMessage.contains("cannot be null") || errorMessage.contains("NOT NULL")) {
            titulo = "Campo obligatorio";
            tipo = "warning";
            mensajeUsuario = "Todos los campos obligatorios deben ser completados; por favor verifica el formulario.";
                         }


        model.addAttribute("titulo", titulo);
        model.addAttribute("mensaje", mensajeUsuario);
        model.addAttribute("tipo", tipo);
        model.addAttribute("volverUrl", "/");
        return "error";
    }

    // Para capturar excepciones en donde no se cumplan los constraints
    @ExceptionHandler
    public String handleConstraintException(ConstraintViolationException ex, Model model){
        String mensajeUsuario = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)    // obtiene "La edad debe ser mayor o igual a 18"
            .findFirst()
            .orElse("Error de validación");

        String titulo = "Error en la operación";
        String tipo = "warning";

        model.addAttribute("titulo", titulo);
        model.addAttribute("mensaje", mensajeUsuario);
        model.addAttribute("tipo", tipo);
        model.addAttribute("volverUrl", "/");
        return "error";
    }
}
