package com.shopify.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  This is the basic Entity class of Product, and other attributes can be implemented easily through this file.
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    //auto Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //unique title
    @Column(nullable = false)
    private String title;

    //product company
    @Column(nullable = false)
    private String company;

    //quantity
    private int quantity;
}
