package com.endava.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endava.restaurant.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
