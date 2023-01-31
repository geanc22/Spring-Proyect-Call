package com.bockycall.appsilimitadas.controllers;

import com.bockycall.appsilimitadas.entities.App;
import com.bockycall.appsilimitadas.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("apps-ilimitadas/apps")
public class AppController {
    @Autowired
    private IAppService service;

    @GetMapping
    public Iterable<App> listarApps() {
        return service.listarApps();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> buscarAppPorName(@PathVariable String name) {
        App app;
        Map<String, Object> response = new HashMap<>();
        try {
            app = service.buscarPorName(name);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (app == null) {
            response.put("mensaje", "La app ".concat(name).concat(" no existe en la base de datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(app, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarApp(@RequestBody App app) {

        App appNueva;
        Map<String, Object> response = new HashMap<>();
        try {
            appNueva = service.guardarApp(app);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La app ha sido creada con éxito");
        response.put("app", appNueva);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> actualizarApp(@PathVariable String name, @RequestBody App app) {
        App appActual = service.buscarPorName(name);
        App appUpdated;
        Map<String, Object> response = new HashMap<>();
        if (appActual == null) {
            response.put("mensaje", "Error: no se pudo editar, la app: "
                    .concat(name.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            appActual.setName(app.getName());
            appUpdated = service.guardarApp(appActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la app en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La app ha sido actualizado con éxito!");
        response.put("app", appUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> eliminarApp(@PathVariable String name) {
        App app = service.buscarPorName(name);
        Map<String, Object> response = new HashMap<>();
        if (app == null) {
            response.put("mensaje", "Error: no se pudo eliminar, la app: "
                    .concat(name.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            service.eliminarApp(app);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la app de la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La app ha sido eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
