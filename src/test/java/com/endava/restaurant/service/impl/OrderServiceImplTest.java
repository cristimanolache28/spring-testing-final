package com.endava.restaurant.service.impl;

import static com.endava.mocks.ProductMock.getProducts;
import static com.endava.mocks.EmployeeMock.getMockEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.endava.restaurant.constants.Constants;
import com.endava.restaurant.entity.Employee;
import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;
import com.endava.restaurant.repository.EmployeeRepository;
import com.endava.restaurant.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final String BADGE_CODE_VALID = "cdcf6efc-a86c-11ec-b909-0242ac120002";
    public static final String BADGE_CODE_INVALID = "invalid";

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Test
    void test_registerOrder_successfully() {
        List<Product> products = getProducts();
        Employee employee = getMockEmployee(BADGE_CODE_VALID);

        when(employeeRepository.getByBadgeCode(BADGE_CODE_VALID)).thenReturn(Optional.of(employee));

        orderService.registerOrder(products, BADGE_CODE_VALID);

        verify(orderRepository).save(orderCaptor.capture());
        verify(orderRepository, times(1)).save(orderCaptor.capture());
        assertEquals(employee, orderCaptor.getValue().getEmployee());
        assertEquals(27, orderCaptor.getValue().getTotal());
        assertTrue(LocalDateTime.now().isAfter(orderCaptor.getValue().getOrderDate()));
        System.out.println(LocalDateTime.now().getDayOfYear());
        assertEquals(LocalDateTime.now().getDayOfYear(), orderCaptor.getValue().getOrderDate().getDayOfYear());
    }

    @Test
    void test_registerOrder_invalidBadgeCode_throwsException() {
        List<Product> products = getProducts();

        when(employeeRepository.getByBadgeCode(BADGE_CODE_INVALID)).thenReturn(Optional.empty());

        ProductException exception = assertThrows(ProductException.class, () -> orderService.registerOrder(products, BADGE_CODE_INVALID));

        verify(orderRepository, never()).save(any());
        assertEquals(MessageFormat.format(Constants.Error.INVALID_BADGE_CODE, BADGE_CODE_INVALID), exception.getMessage());
    }

    @Test
    void test_registerOrder_emptyProductList_throwsException() {
        ProductException exception = assertThrows(ProductException.class, () -> orderService.registerOrder(Collections.emptyList(), BADGE_CODE_VALID));

        verify(orderRepository, never()).save(any());
        assertEquals(Constants.Error.INVALID_ORDER, exception.getMessage());
    }
}
