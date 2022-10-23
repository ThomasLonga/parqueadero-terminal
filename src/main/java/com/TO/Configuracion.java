package com.TO;

import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="configuracion")
public class Configuracion implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int idConfiguracion;
    @Column(name="nombreparqueadero")
    private String nombreParqueadero;
    @Column(name="valorhoracarro")
    private Double valorHoraCarro;
    @Column(name="valorhoramoto")
    private Double valorHoraMoto;
    @Column(name="valorfraccarro")
    private Double valorFracCarro;
    @Column(name="valorfracmoto")
    private Double valorFracMoto;
    @Column(name="valordiacarro")
    private Double valorDiaCarro;
    @Column(name="valordiamoto")
    private Double valorDiaMoto;
    @Column(name="valorsemcarro")
    private Double valorSemCarro;
    @Column(name="valorsemmoto")
    private Double valorSemMoto;
    @Column(name="valormescarro")
    private Double valorMesCarro;
    @Column(name="valormesmoto")
    private Double valorMesMoto;
}
