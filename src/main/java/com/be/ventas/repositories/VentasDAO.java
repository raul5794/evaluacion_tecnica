package com.be.ventas.repositories;

import com.be.ventas.entities.DetalleVentas;
import com.be.ventas.entities.Ventas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class VentasDAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public void crearVenta(Ventas ventas) {
        em.persist(ventas);
        for (DetalleVentas dv : ventas.getDetalleVentasSet()) {
            em.persist(dv);
        }

    }

    ;

    public void eliminarVenta(Ventas ventas1) {
        Ventas ventas = em.merge(ventas1);
        for(DetalleVentas dv : ventas.getDetalleVentasSet()){
            em.remove(dv);
        }
        em.remove(ventas);
            }

    ;
}
