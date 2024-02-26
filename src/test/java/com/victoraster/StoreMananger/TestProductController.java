package com.victoraster.StoreMananger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.victoraster.StoreMananger.controllers.ProductController;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.repository.ProductRepository;
import com.victoraster.StoreMananger.services.ProductService;

@ExtendWith(MockitoExtension.class)
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

        // when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        Product result = productService.getProductById(productId);
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(result.getName(), "mockedProduct");
    }
}
