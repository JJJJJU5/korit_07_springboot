package com.example.cardatabase4;

import com.example.cardatabase4.domain.CarRepository;
import com.example.cardatabase4.domain.OwnerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Cardatabase4Application {
	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
    public Cardatabase4Application(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Cardatabase4Application.class, args);
	}

}
