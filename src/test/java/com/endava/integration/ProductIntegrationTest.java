package com.endava.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.endava.restaurant.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = {"classpath:data-test.sql"})
//@SqlGroup({ // Setups .sql files to be run on specific triggers. In this case, before each test method the script will be ran.
//        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data-test.sql"),
//})
public class ProductIntegrationTest {

    public static final String VALID_CATEGORY = "Desert";
    public static final String BASE_PATH = "/";

    public static final String PRODUCTS = "products";
    protected static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    private static final String queryParamCategory = "?category=";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_getProducts_withSuccess() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(
                get(BASE_PATH + PRODUCTS + queryParamCategory + VALID_CATEGORY)
                        .servletPath(BASE_PATH + PRODUCTS))
                .andReturn();

        assertNotNull(mvcResult);
        //System.out.println(mvcResult.getResponse().getContentAsString());
        List<Product> resultedProducts = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertTrue(resultedProducts.size() == 3);
    }

}
