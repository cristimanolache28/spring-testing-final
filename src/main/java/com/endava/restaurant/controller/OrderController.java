package com.endava.restaurant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> registerOrder(@RequestBody List<Product> products, @RequestParam("badgeCode") String waiterBadgeCode) {
        return new ResponseEntity<>(orderService.registerOrder(products, waiterBadgeCode), HttpStatus.CREATED);
    }
}
