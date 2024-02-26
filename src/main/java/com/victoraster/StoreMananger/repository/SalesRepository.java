package com.victoraster.StoreMananger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victoraster.StoreMananger.models.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}

