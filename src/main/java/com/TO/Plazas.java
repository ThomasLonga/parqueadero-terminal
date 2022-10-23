package com.TO;

import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="plazas")
public class Plazas implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int idPlazas;
    private String codigo;
    private String tipo;
    private String estado;
}
