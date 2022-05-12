package com.endava.restaurant.entity;

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
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    @OneToMany
    @JoinTable(
            name="product_ingredients",
            joinColumns = @JoinColumn( name="id_product"),
            inverseJoinColumns = @JoinColumn( name="id_ingredient")
    )
    private List<Ingredient> ingredients;
}
