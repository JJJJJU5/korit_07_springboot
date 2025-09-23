package com.example.cardatabase.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id  //Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto.increment
    private long id;
    private String brand, model, color , registrationNumber;
    private int modelYear, price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // JPA는 기본 생성자가 필수적으로 요구된다.
    public Car(String brand, String model, String color, String registrationNumber, int modelYear, int price,Owner owner)  {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
        this.owner = owner;

    }
}
