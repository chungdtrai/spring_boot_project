package com.chung.jpapaging_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
@ToString(exclude = "category")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_id")
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_color",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name="color_id"))
    private Set<Color> colors;
}
