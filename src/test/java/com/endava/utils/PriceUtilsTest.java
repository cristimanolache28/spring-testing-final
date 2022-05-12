package com.endava.utils;

import static com.endava.mocks.ProductMock.getProducts;
import static com.endava.restaurant.constants.Constants.Error.INVALID_DISCOUNT;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;

class PriceUtilsTest {

    @Test
    @DisplayName(
            "WHEN applying valid discount of 15 " +
                    "ON a given price of 100.0 " +
                    "THEN reduced price is returned 85.0")
    void applyDiscount_withValidDiscount_returnsCorrectPrice() {
        final Double result = PriceUtils.applyDiscount(100.0, 15);

        assertEquals(85.0, result);
    }

    @Test
    @DisplayName(
            "WHEN applying invalid discount of 55 " +
                    "ON a given price of 100.0 " +
                    "THEN exception is thrown")
    public void applyDiscount_withInvalidDiscount_throwsException() {
        final ProductException exception = assertThrows(ProductException.class,
                () -> PriceUtils.applyDiscount(100.0, 55));

        assertEquals(INVALID_DISCOUNT, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 7, 16, 19})
    @DisplayName(
            "WHEN applying valid discount " +
                    "ON a given price of 100.0 " +
                    "THEN reduced price is returned")
    void applyDiscount_withValidDiscount_returnsCorrectPrice_parametrizedTest(int discount) {
        final Double result = PriceUtils.applyDiscount(100.0, discount);

        assertEquals(100.0 - discount, result);
    }

    @ParameterizedTest
    @CsvSource(value = {"2:98.0:100", "15:85.0:100"}, delimiter = ':')
    @DisplayName(
            "WHEN applying valid discount " +
                    "ON a given price of 100.0 " +
                    "THEN reduced price is returned")
    void applyDiscount_withValidDiscount_returnsCorrectPrice_parametrizedTest(int discount, double expected, double price) {
        final Double result = PriceUtils.applyDiscount(price, discount);

        assertEquals(expected, result);
    }

    @Disabled
    @ParameterizedTest
    @NullSource
    @DisplayName(
            "WHEN applying null discount " +
                    "ON a given price of 100.0 " +
                    "THEN exception is thrown")
    void applyDiscount_withNullDiscount_throwsException_parametrizedTest(Integer discount) {
        PriceUtils.applyDiscount(100.0, discount);
    }

    @Test
    void getTotalPrice_withProductList_returnsTotalPrice() {
        final List<Product> products = getProducts();

        final Double totalPrice = PriceUtils.getTotalPrice(products);

        assertEquals(27.0, totalPrice);
    }

    @ParameterizedTest
    @MethodSource("com.endava.mocks.ProductMock#getProductsList")
    void getTotalPrice_withProductList_returnsTotalPrice_parametrizedTest(List<Product> products) {
        final Double totalPrice = PriceUtils.getTotalPrice(products);

        assertEquals(27.0, totalPrice);
    }

    @ParameterizedTest
    @EmptySource
    void getTotalPrice_withEmptyProductList_returnsTotalPrice_parametrizedTest(List<Product> products) {
        final Double totalPrice = PriceUtils.getTotalPrice(products);

        assertAll(
                () -> assertNotNull(totalPrice),
                () -> assertEquals(0.0, totalPrice)
        );
    }


    /*
    @Test
    public void test_[method]_[parameters/test_conditions]_[expected_outcome]() {
    }

    @Test
    public void [method]_shouldReturn [expected_outcome]_when[parameters/test_conditions]() {

    }

    @Test
    public void [method]_shouldFail [expected_outcome]_when[parameters/test_conditions]() {

    }
    */
}
