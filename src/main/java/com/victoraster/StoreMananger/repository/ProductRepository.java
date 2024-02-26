package com.victoraster.StoreMananger.repository;

import com.victoraster.StoreMananger.models.Product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
