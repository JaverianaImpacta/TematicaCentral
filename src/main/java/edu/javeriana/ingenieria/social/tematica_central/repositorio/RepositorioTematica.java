package edu.javeriana.ingenieria.social.tematica_central.repositorio;

import edu.javeriana.ingenieria.social.tematica_central.dominio.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTematica extends JpaRepository<Tematica, Integer> {
    boolean existsByDescripcion(String descripcion);
}
