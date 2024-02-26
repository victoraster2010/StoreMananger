package com.victoraster.StoreMananger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "sales_products")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sales sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Sales getSale() {
        return sale;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }

}