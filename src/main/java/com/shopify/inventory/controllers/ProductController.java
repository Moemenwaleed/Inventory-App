package com.shopify.inventory.controllers;

import com.shopify.inventory.dtos.ProductDto;
import com.shopify.inventory.models.Product;
import com.shopify.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** This file controls all the HTTP requests that affects the products.
 *
 * GET Request /api/products/ will list all products in a JSON list
 * POST Request /api/products/, along with the basic Product variables, will create a new Product
 * PUT Request /api/products/{id}, along with the basic Product variables, will update existing Product
 * DELETE Request /api/products/{id}, will delete the Product associated with the id.
 *
 * Keep in mind that data in POST/PUT are sent as a ProductDto object, which allows a dynamic way of retrieving data
 * to be able to implement other connected tables/features in the future.
 */

@RestController
@RequestMapping("/api/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productService.update(id, productDto);
    }

}
