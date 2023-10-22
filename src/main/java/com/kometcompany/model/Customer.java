package com.kometcompany.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * clase de la entidad Cliente.
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Data
@Entity
@Table(name = "tblcustomerpt")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "markdown")
    private BigDecimal markdown;

}