package com.endava.component;

import static com.endava.mocks.EmployeeMock.getMockEmployee;
import static com.endava.mocks.ProductMock.getProducts;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.endava.restaurant.entity.Employee;
import com.endava.restaurant.entity.Order;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.repository.EmployeeRepository;
import com.endava.restaurant.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderComponentTest {

    private static final String BADGE_CODE_VALID = "cdcf6efc-a86c-11ec-b909-0242ac120002";
    public static final String BADGE_CODE_INVALID = "invalid";
    private static final String BASE_PATH = "/";
    private static final String ORDERS = "orders";
    private static final String queryParamBadgeCode = "?badgeCode=";
    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private OrderRepository orderRepository;

    public static String parseFromObject(Object objectToBeParsed) throws JsonProcessingException {
        return mapper.writeValueAsString(objectToBeParsed);
    }

    @Test
    void registerOrder_successfully() throws Exception {
        List<Product> products = getProducts();
        Employee employee = getMockEmployee(BADGE_CODE_VALID);

        when(employeeRepository.getByBadgeCode(BADGE_CODE_VALID)).thenReturn(Optional.of(employee));
        when(orderRepository.save(any())).thenReturn(Order.builder().id(1).build());

        final MvcResult mvcResult = mockMvc.perform(
                post(BASE_PATH + ORDERS + queryParamBadgeCode + BADGE_CODE_VALID)
                        .servletPath(BASE_PATH + ORDERS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(parseFromObject(products)))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(mvcResult);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void registerOrder_failure() throws Exception {
        List<Product> products = getProducts();
        Employee employee = getMockEmployee(BADGE_CODE_INVALID);

        when(employeeRepository.getByBadgeCode(BADGE_CODE_INVALID)).thenReturn(Optional.empty());

        final MvcResult mvcResult = mockMvc.perform(
                post(BASE_PATH + ORDERS + queryParamBadgeCode + BADGE_CODE_VALID)
                        .servletPath(BASE_PATH + ORDERS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(parseFromObject(products)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertNotNull(mvcResult);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
