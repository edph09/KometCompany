package com.kometcompany.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
/**
 * clase de la entidad Compania.
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Data
@Entity
@Table(name = "tblcompanypt")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
