package com.example.demo_product_choreogrphy.entity;

import com.example.demo_product_choreogrphy.enumEntity.ProductEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String thumbnails;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String detail;
    private Integer quantity;
    private Integer price;
    private ProductEnum status;

    public Product(String name, String thumbnails, Integer quantity, Integer price, ProductEnum status) {
        this.name = name;
        this.thumbnails = thumbnails;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }
}
