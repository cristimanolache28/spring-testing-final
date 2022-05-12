package com.endava.component;

import static com.endava.mocks.ProductMock.getMockedProduct;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.endava.restaurant.entity.Product;
import com.endava.restaurant.repository.CategoryRepository;
import com.endava.restaurant.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductComponentTest {

    public static final String VALID_CATEGORY = "Desert";
    public static final String BASE_PATH = "/";

    public static final String PRODUCTS = "products";
    protected static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    private static final String queryParamCategory = "?category=";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void test_getProducts_withSuccess() throws Exception {
        when(categoryRepository.existsCategoryByName(VALID_CATEGORY)).thenReturn(true);
        when(productRepository.findByCategory_Name(VALID_CATEGORY)).thenReturn(Collections.singletonList(getMockedProduct()));

        final MvcResult mvcResult = mockMvc.perform(
                //get("/products?category=Desert")
                get(BASE_PATH + PRODUCTS + queryParamCategory + VALID_CATEGORY)
                        .servletPath(BASE_PATH + PRODUCTS))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(mvcResult);
        //System.out.println(mvcResult.getResponse().getContentAsString());
        List<Product> resultedProducts = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {
        });
        assertTrue(resultedProducts.size() == 1);
    }

    @Test
    @Timeout(unit = TimeUnit.MILLISECONDS, value = 1000)
    void test_getProductById_withSuccess() throws Exception {
        final Product product = getMockedProduct();
        when(productRepository.findProductById(any())).thenReturn(getMockedProduct());

        final MvcResult mvcResult = this.mockMvc.perform(
                get(BASE_PATH + PRODUCTS + "/{id}", product.getId())
                        .servletPath(BASE_PATH + PRODUCTS + "/" + product.getId()))
                .andReturn();
        // .andExpect(status().isOk());

        assertNotNull(mvcResult);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
