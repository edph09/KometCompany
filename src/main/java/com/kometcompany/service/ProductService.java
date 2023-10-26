package com.kometcompany.service;

import com.kometcompany.model.Product;
import com.kometcompany.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listarProductos() {
        return productRepository.findAll();
    }
}