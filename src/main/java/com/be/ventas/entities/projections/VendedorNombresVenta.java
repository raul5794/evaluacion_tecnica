package com.be.ventas.entities.projections;

import org.springframework.beans.factory.annotation.Value;

public interface VendedorNombresVenta {

    @Value("#{target.nombres + ' '+target.apellidos}")
    String getFullName();

    Double getVentas();
}
