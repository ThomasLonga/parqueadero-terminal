package com.TO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="facturas")
public class Facturas implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int idFacturas;
    private String placa;
    @Column(name="tipocontrato")
    private String tipoContrato;
    @Column(name="fechaentrada")
    private Date fechaEntrada;
    @Column(name="fechasalida")
    private Date fechaSalida;
    @Column(name="fechafactura")
    private Date fechaFactura;
    private Double valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_personas")
    private Personas persona;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plazas")
    private Plazas plaza;
    @Column(name="codigofactura")
    private String codigoFactura;
}
