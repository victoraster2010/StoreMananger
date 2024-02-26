package com.victoraster.StoreMananger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victoraster.StoreMananger.models.ItemVenda;

public interface SalesProducts extends JpaRepository<ItemVenda, Long> {

}
