package com.victoraster.StoreMananger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.victoraster.StoreMananger.controllers.ProductController;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.repository.ProductRepository;
import com.victoraster.StoreMananger.services.ProductService;



public class TestProductController {

    @Mock
    private ProductRepository productRepository;
    
    @InjectMocks
    private ProductService productService;

    @InjectMocks
    private ProductController productController;


    @Test
    public void testGetById() {
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("mockedProduct");
    }
}
