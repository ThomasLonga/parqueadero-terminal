package com.TO;

import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int idUsuarios;
    private String usuario;
    private String clave;
    private String rol;
    //private int idPersonas;
    
    //@JoinTable(name="personas")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_personas")
    private Personas persona;
}
