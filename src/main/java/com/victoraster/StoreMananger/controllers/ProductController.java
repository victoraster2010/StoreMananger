package com.victoraster.StoreMananger.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victoraster.StoreMananger.dto.CreateProductDTO;
import com.victoraster.StoreMananger.dto.UpdateProductDTO;
import com.victoraster.StoreMananger.models.Product;
import com.victoraster.StoreMananger.services.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts() {
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO name) {
        return new ResponseEntity<Product>(productService.createProduct(name.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> findByName(@RequestParam String name) {
        List<Product> searchForName = productService.findByName(name);
        return new ResponseEntity<>(searchForName, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO update) {
        Product updated = productService.updateProduct(id, update);
        return new ResponseEntity<Product>(updated, HttpStatus.OK);

    }
}
