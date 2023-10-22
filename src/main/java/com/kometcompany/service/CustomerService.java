package com.kometcompany.service;

import com.kometcompany.model.Customer;
import com.kometcompany.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que contiene la logica de negocio de la entidad Cliente .
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Service
public class CustomerService {
    private ICustomerRepository customerRepository;

    /**
     * Constructor  .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    /**
     * Metodo que me obtiene el cliente por el ID  .
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
