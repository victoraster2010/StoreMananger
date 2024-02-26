package com.victoraster.StoreMananger.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column
    private Timestamp date;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    public Timestamp getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
