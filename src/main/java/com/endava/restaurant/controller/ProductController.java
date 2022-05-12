package com.endava.restaurant.controller;


import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.endava.restaurant.entity.Product;
import com.endava.restaurant.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts(@RequestParam("category") String category) {
        return ResponseEntity.ok(productService.getProducts(category));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
