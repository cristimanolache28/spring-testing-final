package com.endava.restaurant.service;

import java.util.List;

import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;

public interface OrderService {

    Order registerOrder(List<Product> products, String waiterBadgeCode);
}
