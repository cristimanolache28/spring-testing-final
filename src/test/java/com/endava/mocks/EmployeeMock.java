package com.endava.mocks;

import static lombok.AccessLevel.PRIVATE;

import com.endava.restaurant.entity.Employee;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class EmployeeMock {

    public static Employee getMockEmployee(String badgeCode) {

        return Employee.builder()
                .badgeCode(badgeCode)
                .name("Mock Employee")
                .jobTitle("waiter")
                .id(1)
                .build();
    }
}
