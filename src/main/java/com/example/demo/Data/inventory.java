package com.example.demo.Data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plant")
public class inventory {
    @Id
    @GeneratedValue
    private Long id;
    @Nationalized
    private String name;
    private String color;
    @Column(precision = 12, scale = 4)
    private BigDecimal price; // BigDecimal is the standard Java class for currency math

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
