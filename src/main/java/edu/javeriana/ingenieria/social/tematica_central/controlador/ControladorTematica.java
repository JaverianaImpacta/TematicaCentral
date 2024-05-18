package edu.javeriana.ingenieria.social.tematica_central.controlador;

import edu.javeriana.ingenieria.social.tematica_central.dominio.Tematica;
import edu.javeriana.ingenieria.social.tematica_central.servicio.ServicioTematica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tematicasCentrales")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorTematica {
    @Autowired
    private ServicioTematica servicio;

    @GetMapping("listar")
    public List<Tematica> obtenerTematicas(){
        return servicio.obtenerTematicas();
    }

    @GetMapping("obtener")
    public ResponseEntity<Tematica> obtenerTematica(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerTematica(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerTematica(id), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Tematica> crearTematica(@RequestBody Tematica tematica){
        if(tematica == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.tematicaExiste(tematica.getDescripcion()) || servicio.tematicaExiste(tematica.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearTematica(tematica), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Tematica> actualizarTematica(@RequestParam Integer id, @RequestBody Tematica tematica){
        if(id == null || tematica == null || !id.equals(tematica.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarTematica(id, tematica) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(servicio.tematicaExiste(tematica.getDescripcion())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tematica, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarTematica(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarTematica(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
