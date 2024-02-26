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
import org.mockito.junit.jupiter.MockitoExtension;


import com.victoraster.StoreMananger.controllers.ProductController;
import com.victoraster.StoreMananger.dto.UpdateProductDTO;
import com.victoraster.StoreMananger.exceptions.InvalidFields;
import com.victoraster.StoreMananger.exceptions.ResourceNotFoundException;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.repository.ProductRepository;
import com.victoraster.StoreMananger.services.ProductService;

@ExtendWith(MockitoExtension.class)
public class TestProductService {

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

    @Test
    public void testGetById_ThrowsError_ResourceNotFound() {

        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
    }

    @Test
    public void testGetAll() {
        Product oneProduct = new Product();
        Product secondProduct = new Product();
        oneProduct.setId(1L);
        oneProduct.setName("Mangá one pumchman");
        secondProduct.setId(2L);
        secondProduct.setName("Carro do Batman");
        List<Product> products = new ArrayList<>();
        products.add(secondProduct);
        products.add(oneProduct);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> returnedProducts = productService.getAllProducts();
        assertNotNull(returnedProducts);
        assertEquals(returnedProducts.size(), 2);
        assertEquals(returnedProducts, products);
    }

    @Test
    public void createProductSucessfulTest() {
        Product createdProduct = new Product();
        createdProduct.setName("RB20");

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product toSave = invocation.getArgument(0);
            toSave.setId(1L);
            return toSave;
        });
        Product queryProduct = productService.createProduct("RB20");
        assertEquals(queryProduct.getName(), "RB20");
    }

    @Test
    void createProduct_ThrowError_InvalidArgument() {
        assertThrows(InvalidFields.class, () -> {
            productService.createProduct("");
        });
    }

    @Test
    public void getByName_SucessfulTest() {
        List<Product> travelBoots = new ArrayList<>();
        Product item = new Product();
        item.setId(1L);
        item.setName("Travel Boots");
        travelBoots.add(item);
        when(productRepository.findByName("Travel Boots")).thenReturn(travelBoots);
        List<Product> testList = productService.findByName("Travel Boots");
        assertNotNull(testList);
        assertEquals(1, testList.size());
    }

    @Test
    public void getByName_Error_InvalidFields() {
        assertThrows(InvalidFields.class, () -> {
            productService.findByName("");
        });
    }

    @Test
    public void getByName_Error_ResourceNotFound() {

        when(productRepository.findByName("owo")).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findByName("owo");
        });
    }

    @Test
    public void updateProduct_Sucessful() {
        Product foundProduct = new Product();
        foundProduct.setId(1L);
        foundProduct.setName("Moon");

        when(productRepository.findById(1L)).thenReturn(Optional.of(foundProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product toSave = invocation.getArgument(0);
            toSave.setName("Sun");
            return toSave;
        });
        UpdateProductDTO dataToUpdate = new UpdateProductDTO( "Sun");
        Product updatedProject = productService.updateProduct(1L, dataToUpdate);
        assertNotNull(updatedProject);
        assertEquals(dataToUpdate.name(), updatedProject.getName());

    }

    @Test
    public void updateProduct_Error_InvalidFields() {
        UpdateProductDTO product = new UpdateProductDTO("");
        assertThrows(InvalidFields.class, () -> {
            productService.updateProduct(1L, product);
        });
    }

    @Test
    public void updateProduct_Error_ResourceNotFound() {
        UpdateProductDTO product = new UpdateProductDTO("Aviões");

        when(productRepository.findById(4L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.updateProduct(4L, product);
        });
    }
}
