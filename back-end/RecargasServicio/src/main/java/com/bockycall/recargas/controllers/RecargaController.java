package com.bockycall.recargas.controllers;

import com.bockycall.recargas.entities.Recarga;
import com.bockycall.recargas.services.IRecargaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recargas")
public class RecargaController {
    @Autowired
    private IRecargaService service;

    @GetMapping
    public ResponseEntity<?> listarRecargas() {
        return new ResponseEntity<>(service.listarRecargas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRecarga(@PathVariable Long id) {
        Recarga recarga = service.buscarRecarga(id);
        Map<String, Object> response = new HashMap<>();
        if (recarga == null) {
            response.put("mensaje", "la recarga no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recarga, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarRecarga(@Valid @RequestBody Recarga recarga, BindingResult result) {
        Recarga recargaGuardada;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            recargaGuardada = service.guardarRecarga(recarga);
        } catch (DataAccessException e) {
            response.put("mensaje", "error al guardar la recarga");
            response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "recarga guardada con exito");
        response.put("recarga", recargaGuardada);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRecarga(@PathVariable Long id, @Valid @RequestBody Recarga recarga, BindingResult result) {
        Recarga recargaEncontrada = service.buscarRecarga(id);
        Recarga recargaActualizada;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (recargaEncontrada == null) {
            response.put("mensaje", "la recarga no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            recargaEncontrada.setPrice(recarga.getPrice());
            recargaEncontrada.setValidity(recarga.getValidity());
            recargaActualizada = service.guardarRecarga(recargaEncontrada);
        } catch (DataAccessException e) {
            response.put("mensaje", "error al guardar la recarga");
            response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "recarga actualizada exitosamente");
        response.put("recarga", recargaActualizada);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecarga(@PathVariable Long id) {
        Recarga recargaEncontrada = service.buscarRecarga(id);
        Map<String, Object> response = new HashMap<>();
        if (recargaEncontrada == null) {
            response.put("mensaje", "la recarga no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.eliminarRecarga(recargaEncontrada);
        response.put("mensaje", "recarga eliminada con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
