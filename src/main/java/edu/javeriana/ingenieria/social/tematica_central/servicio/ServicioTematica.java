package edu.javeriana.ingenieria.social.tematica_central.servicio;

import edu.javeriana.ingenieria.social.tematica_central.dominio.Tematica;
import edu.javeriana.ingenieria.social.tematica_central.repositorio.RepositorioTematica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTematica {
    @Autowired
    private RepositorioTematica repositorio;

    public List<Tematica> obtenerTematicas(){
        return repositorio.findAll();
    }

    public Tematica obtenerTematica(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Tematica crearTematica(Tematica tematica){
        return repositorio.save(tematica);
    }

    public Tematica actualizarTematica(Integer id, Tematica tematica){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        tematica.setId(id);
        return repositorio.save(tematica);
    }
    public Tematica borrarTematica(Integer id){
        Tematica aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean tematicaExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean tematicaExiste(String descripcion){
        return repositorio.existsByDescripcion(descripcion);
    }
}
