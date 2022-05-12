package com.endava.restaurant.service.impl;

import static com.endava.restaurant.constants.Constants.Error.INVALID_BADGE_CODE;
import static com.endava.restaurant.constants.Constants.Error.INVALID_ORDER;
import static com.endava.utils.PriceUtils.getTotalPrice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.endava.restaurant.entity.Employee;
import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;
import com.endava.restaurant.repository.EmployeeRepository;
import com.endava.restaurant.repository.OrderRepository;
import com.endava.restaurant.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Order registerOrder(List<Product> products, String waiterBadgeCode) {
        if (products.isEmpty()) {
            throw new ProductException(INVALID_ORDER);
        }
        return orderRepository.save(createOrder(products, waiterBadgeCode));
    }

    private Order createOrder(List<Product> products, String waiterBadgeCode) {
        return Order.builder()
                .products(products)
                .orderDate(LocalDateTime.now())
                .total(getTotalPrice(products))
                .discount(0.0)
                .employee(getEmployee(waiterBadgeCode))
                .build();
    }

    private Employee getEmployee(String badgeCode) {
        return employeeRepository.getByBadgeCode(badgeCode)
                .orElseThrow(() -> new ProductException(INVALID_BADGE_CODE, badgeCode));
    }
}
