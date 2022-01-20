package com.shopify.inventory.services;

import com.shopify.inventory.dtos.ProductDto;
import com.shopify.inventory.models.Product;
import com.shopify.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class is responsible for all the CRUD actions on any of the products
 * Any other actions can be implemented in this file,
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product add(ProductDto productDto) {
        Product product = Product.builder()
                .company(productDto.getCompany())
                .title(productDto.getTitle())
                .quantity(productDto.getQuantity())
                .build();
        return productRepository.save(product);
    }

    public void update(long id, ProductDto productDto) {
        if(productRepository.existsById(id)){
            Product product = Product.builder()
                    .id(productDto.getId())
                    .company(productDto.getCompany())
                    .title(productDto.getTitle())
                    .quantity(productDto.getQuantity())
                    .build();
            productRepository.save(product);
        }
    }

    public void deleteById(long id){
        if(productRepository.existsById(id))
            productRepository.deleteById(id);
        //Handling errors could be implemented here, but in our case, It's just through UI.
    }

}
