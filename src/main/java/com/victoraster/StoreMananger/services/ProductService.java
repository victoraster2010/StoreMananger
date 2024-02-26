package com.victoraster.StoreMananger.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victoraster.StoreMananger.dto.CreateProductDTO;
import com.victoraster.StoreMananger.dto.UpdateProductDTO;
import com.victoraster.StoreMananger.exceptions.InvalidFields;
import com.victoraster.StoreMananger.exceptions.ResourceNotFoundException;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.repository.ProductRepository;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> findById = productRepository.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    public Product createProduct(String name) {
        if (name.isBlank() || name.isEmpty()) {
            throw new InvalidFields("Nome do produto vazio ou inválido");
        }
        Product product = new Product();
        product.setName(name);
        Product createProduct = productRepository.save(product);
        return createProduct;
    }

    public List<Product> findByName(String name) {
        if (name.isEmpty() || name.equals("")) {
            throw new InvalidFields("Nome do produto vazio ou inválido");
        }
        List<Product> findByName = productRepository.findByName(name);
        if (findByName.isEmpty()) {
            throw new ResourceNotFoundException("Produto Inexistente");
        }
        return productRepository.findByName(name);
    }

    public Product updateProduct(Long id, UpdateProductDTO product) {
        Optional<Product> findById = productRepository.findById(id);
        if (findById.isEmpty()) {
            throw new ResourceNotFoundException("Produto Inexistente");
        }
        Product productNew = findById.get();
        productNew.setName(product.getName());
        Product updatedProduct = productRepository.save(productNew);
        return updatedProduct;
    }

}
