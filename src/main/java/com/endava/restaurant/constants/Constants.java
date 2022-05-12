package com.endava.restaurant.constants;

public class Constants {

    public static class Error {

        public static final String INVALID_CATEGORY = "Invalid category: {0}";
        public static final String INVALID_DISCOUNT = "Discount offered is to high: {0}. Maximum allowed discount is 45%";
        public static final String INVALID_ORDER = "Invalid order";
        public static final String INVALID_BADGE_CODE = "No employee found for badge code: {0}";

        private Error() {
        }
    }
}
