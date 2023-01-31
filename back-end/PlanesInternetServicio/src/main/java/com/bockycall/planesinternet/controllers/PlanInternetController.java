package com.bockycall.planesinternet.controllers;

import com.bockycall.planesinternet.entities.InfoExtra;
import com.bockycall.planesinternet.entities.PlanInternet;
import com.bockycall.planesinternet.request.InfoExtraRequest;
import com.bockycall.planesinternet.request.PlanInternetRequest;
import com.bockycall.planesinternet.response.PlanInternetResponse;
import com.bockycall.planesinternet.services.InfoExtraService;
import com.bockycall.planesinternet.services.PlanInternetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/planes-internet")
public class PlanInternetController {
    @Autowired
    private PlanInternetService planService;

    @Autowired
    private InfoExtraService infoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(planService.listarPlanInternet(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPLanInternet(@PathVariable Long id) {
        PlanInternetResponse planInternetResponse = planService.obtenerPlanInternetResponse(id);
        Map<String,Object> response = new HashMap<>();
        if (planInternetResponse == null) {
            response.put("mensaje", "el plan no se encuentra en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(planInternetResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarPlanInternet(@RequestBody PlanInternetRequest planRequest) {
        PlanInternet planInternet = new PlanInternet(planRequest);
        Map<String, Object> response = new HashMap<>();
        try {
            planInternet = planService.guardar(planInternet);
        } catch (DataAccessException e) {
            response.put("mensaje", "error al guardar en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (InfoExtraRequest info : planRequest.getInfoExtras()) {
            InfoExtra infoExtra = new InfoExtra();
            infoExtra.setProperty(info.getProperty());
            infoExtra.setValue(info.getValue());
            infoExtra.setPlanInternet(planInternet);
            try {
                infoService.guardarInfoExtra(infoExtra);
            } catch (DataAccessException e) {
                response.put("mensaje", "error al guardar infoExtras");
                response.put("error", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        PlanInternetResponse planResponse = planService.obtenerPlanInternetResponse(planInternet.getId());
        response.put("mensaje", "datos guardados exitosamente");
        response.put("planInternet", planResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPlanInternet(@PathVariable Long id, @RequestBody PlanInternetRequest planRequest) {
        PlanInternet planInternet = planService.buscar(id);
        Map<String, Object> response = new HashMap<>();
        if (planInternet == null) {
            response.put("mensaje", "el plan no se encuentra en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            planInternet.setTitle(planRequest.getTitle());
            planInternet.setSubtitle(planRequest.getSubtitle());
            planInternet.setPrice(planRequest.getPrice());
            planInternet.setDuration(planRequest.getDuration());
            planService.guardar(planInternet);
            try {
                infoService.actualizarInfoExtra(planRequest.getInfoExtras(), id);
            } catch (DataAccessException e) {
                response.put("mensaje", "error al actualziar infoExtras");
                response.put("error", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "error al actualziar el plan");
            response.put("error", e.getMessage().concat(": " + e.getMostSpecificCause()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PlanInternetResponse planResponse = planService.obtenerPlanInternetResponse(id);
        response.put("mensaje", "El plan ha sido actualziado con exito!");
        response.put("plan", planResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPlanInternet(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        PlanInternet planInternet = planService.buscar(id);
        if (planInternet == null) {
            response.put("mensaje", "el plan no se encuentra en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        planService.eliminar(id);
        response.put("mensaje", "El plan internet fue eliminado exitosamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
