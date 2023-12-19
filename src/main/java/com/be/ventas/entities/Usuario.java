package com.be.ventas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    @Column(name = "nro_docuemnto")
    @NotNull
    @Size(min = 7, max = 10)
    private String nroDocumento;
    @Email
    private String email;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "create_at")
    private Date createAt;
    @NotNull
    @Column(nullable = false,columnDefinition = "numeric default 1")
    private Integer activo;


    public Usuario() {
    }

    public Usuario(@NotNull String nombres, @NotNull String apellidos, @NotNull String nroDocumento, String email, Date createAt, @NotNull Integer activo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.createAt = createAt;
        this.activo = activo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nroDocumento='" + nroDocumento + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                ", activo=" + activo +
                '}';
    }
}
