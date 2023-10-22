package com.kometcompany.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
/**
 * clase de la entidad tipo de cajas.
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Data
@Entity
@Table(name = "tblboxtypept")
public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "length")
    private Double length;

}
