package com.kometcompany.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * clase de la entidad inventario.
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Data
@Entity
@Table(name = "tblinventorypt")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boxtypeid")
    private BoxType boxType;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "companyid")
    private Company company;

    @Column(name = "cubespercarrier")
    private Double cubesPerCarrier;

    @Column(name = "pack")
    private Integer pack;

    @Column(name = "baseprice")
    private BigDecimal basePrice;
}
