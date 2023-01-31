package com.bockycall.appsilimitadas.controllers;

import com.bockycall.appsilimitadas.entities.AppsIlimitadas;
import com.bockycall.appsilimitadas.services.IAppService;
import com.bockycall.appsilimitadas.services.IAppsIlimitadasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/apps-ilimitadas")
public class AppsIlimitadasController {

    @Autowired
    private IAppsIlimitadasService appsIlimitadasService;
    @Autowired
    private IAppService appService;

    @GetMapping
    public Iterable<AppsIlimitadas> listarAppsIlimitas() {
        return appsIlimitadasService.listarAppsIlimitadas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAppsIlimitadas(@PathVariable String id) {
        AppsIlimitadas appsIlimitadas;
        Map<String, Object> response = new HashMap<>();
        try {
            appsIlimitadas = appsIlimitadasService.buscarAppsIlimitadas(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (appsIlimitadas == null) {
            response.put("mensaje", "El pack ID: ".concat(id).concat(" no existe en la base de datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appsIlimitadas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarAppsIlimitadas(@RequestBody AppsIlimitadas appsIlimitadas) {
        AppsIlimitadas appsIlimitadasNueva;
        Map<String, Object> response = new HashMap<>();
        try {
            appsIlimitadasNueva = appsIlimitadasService.guardarAppsIlimitadas(appsIlimitadas);
            appsIlimitadas.getApps().forEach(app -> {
                if (appService.buscarPorName(app.getName()) == null) {
                    appService.guardarApp(app);
                } else {
                    app.setId(appService.buscarPorName(app.getName()).getId());
                }
            });
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El pack ha sido creado con éxito");
        response.put("appsIlimitadas", appsIlimitadasNueva);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAppsIlimitadas(@PathVariable String id, @RequestBody AppsIlimitadas appsIlimitadas) {
        AppsIlimitadas appIlimitadasActual = appsIlimitadasService.buscarAppsIlimitadas(id);
        AppsIlimitadas appIlimitadasUpdated;
        Map<String, Object> response = new HashMap<>();
        if (appIlimitadasActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el pack ID: "
                    .concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            appIlimitadasActual.setTitle(appsIlimitadas.getTitle());
            appIlimitadasActual.setPrice(appsIlimitadas.getPrice());
            appIlimitadasActual.setImg(appsIlimitadas.getImg());
            appIlimitadasActual.setDuration(appsIlimitadas.getDuration());
            appIlimitadasActual.setApps(appsIlimitadas.getApps());
            appsIlimitadas.getApps().forEach(app -> {
                if (appService.buscarPorName(app.getName()) == null) {
                    appService.guardarApp(app);
                } else {
                    app.setId(appService.buscarPorName(app.getName()).getId());
                }
            });
            appIlimitadasUpdated = appsIlimitadasService.guardarAppsIlimitadas(appIlimitadasActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el pack en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El pack ha sido actualizado con éxito!");
        response.put("appsIlimitadas", appIlimitadasUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAppsIlimitadas(@PathVariable String id) {
        AppsIlimitadas appsIlimitadas = appsIlimitadasService.buscarAppsIlimitadas(id);
        Map<String, Object> response = new HashMap<>();
        if (appsIlimitadas == null) {
            response.put("mensaje", "Error: no se pudo eliminar, el paquete ID: "
                    .concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            String nombreImgAnterior = appsIlimitadas.getImg();
            if(nombreImgAnterior != null && nombreImgAnterior.length()>0){
                Path rutaImgAnterior = Paths.get("uploads").resolve(nombreImgAnterior).toAbsolutePath();
                File imgAnterior = rutaImgAnterior.toFile();
                if(imgAnterior.exists() && imgAnterior.canRead()){
                    imgAnterior.delete();
                }
            }
            appsIlimitadasService.eliminarAppsIlimitadas(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el pack de la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El pack ha sido eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*-------------------------------*/
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("img")MultipartFile img, @RequestParam("id") String id){
        Map<String,Object> response = new HashMap<>();

        AppsIlimitadas appsIlimitadas = appsIlimitadasService.buscarAppsIlimitadas(id);
        if(!img.isEmpty()){
            String nombreImg = UUID.randomUUID().toString() +"_"+img.getOriginalFilename().replace(" ","-");
            Path rutaImg = Paths.get("uploads").resolve(nombreImg).toAbsolutePath();

            try {
                Files.copy(img.getInputStream(),rutaImg);
            } catch (IOException e) {
                response.put("mensaje","Error al subir la imagen");
                response.put("error",e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreImgAnterior = appsIlimitadas.getImg();
            if(nombreImgAnterior != null && nombreImgAnterior.length()>0){
                Path rutaImgAnterior = Paths.get("uploads").resolve(nombreImgAnterior).toAbsolutePath();
                File imgAnterior = rutaImgAnterior.toFile();
                if(imgAnterior.exists() && imgAnterior.canRead()){
                    imgAnterior.delete();
                }
            }

            appsIlimitadas.setImg(nombreImg);
            appsIlimitadasService.guardarAppsIlimitadas(appsIlimitadas);
            response.put("appsIlimitadas",appsIlimitadas);
            response.put("mensaje","Ha subido correctamente la imagen: "+nombreImg);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("uploads/img/{imgName:.+}")
    public ResponseEntity<Resource> verImg(@PathVariable String imgName){
        Path rutaImg = Paths.get("uploads").resolve(imgName).toAbsolutePath();
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaImg.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(!recurso.exists() && !recurso.isReadable()){
            throw new RuntimeException("Error no se pudo cargar la imagen: "+imgName);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ recurso.getFilename()+"\"");

        return new ResponseEntity<>(recurso,cabecera,HttpStatus.OK);
    }
}
