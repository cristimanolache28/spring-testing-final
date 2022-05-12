package com.endava.controller;

import static com.endava.mocks.ProductMock.getProducts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.endava.restaurant.controller.OrderController;
import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    public static final String BADGE_CODE_VALID = "cdcf6efc-a86c-11ec-b909-0242ac120002";

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    @Test
    void registerOrder_successfully() {
        final List<Product> products = getProducts();

        when(orderService.registerOrder(products, BADGE_CODE_VALID)).thenReturn(new Order());

        ResponseEntity<Order> order = orderController.registerOrder(products, BADGE_CODE_VALID);

        assertNotNull(order);
        assertEquals(CREATED, order.getStatusCode());
    }
}
