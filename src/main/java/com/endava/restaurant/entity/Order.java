package com.endava.restaurant.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double total;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "id_employer", referencedColumnName = "id")
    private Employee employee;

    @OneToMany
    @JoinTable(
            name="order_products",
            joinColumns = @JoinColumn( name="id_order"),
            inverseJoinColumns = @JoinColumn( name="id_product")
    )
    private List<Product> products;
}
