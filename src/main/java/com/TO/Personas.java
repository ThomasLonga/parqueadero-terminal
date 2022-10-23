package com.TO;

import java.io.Serializable;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="personas")
public class Personas implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int idPersonas;
    private String nombres;
    private String apellidos;
    @Column(name="tipodoc")
    private String tipoDoc;
    @Column(name="numdoc")
    private String numDoc;
    private String direccion;
    private String telefono;
    private String email;
}
