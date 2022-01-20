package com.shopify.inventory.dtos;

import lombok.Data;

import javax.persistence.Column;

/** This class is basically a data transfer object, which retrieves raw data from the user, and then converts
 * them to Product classes.
 *
 * A more interesting example to understand how it would help in the future.
 *
 * Assuming a warehouse id is as well given to allocate the Inventory to a warehouse.
 * warehouse id, is not basically an extra attribute inside the Product, but it is a separate Entity on its own.
 * Therefore, if a warehouse id is as well sent through the ProductDto class, a service would be called from
 * here to create that Entity or to do the necessary changes to that separate Entity.
 *
 */
@Data
public class ProductDto {
    //Attributes posted by the user
    @Column(nullable = true)
    private int id; //Now its nullable, because when it exists, it's going to save existing product

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String title;

    private int quantity;
}
