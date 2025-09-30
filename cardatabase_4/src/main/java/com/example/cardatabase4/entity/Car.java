package com.example.cardatabase4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@NoArgsConstructor // 필수는 아니지만 JPA를 위해 기본 생성자도 추가
public class Car {
    @Id  //Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto.increment
    private long id;

    private String brand, model, color , registrationNumber;
    private int modelYear, price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Car(String brand, String model, String color, String registrationNumber, int modelYear, int price, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
        this.owner = owner;
    }
}
