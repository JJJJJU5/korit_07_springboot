package com.example.cardatabase4;

import com.example.cardatabase4.entity.AppUser;

import com.example.cardatabase4.entity.Car;

import com.example.cardatabase4.entity.Owner;
import com.example.cardatabase4.repository.AppUserRepository;
import com.example.cardatabase4.repository.CarRepository;
import com.example.cardatabase4.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@RequiredArgsConstructor
@SpringBootApplication
public class Cardatabase4Application implements CommandLineRunner {
	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
    private final AppUserRepository appUserRepository;

    public static void main(String[] args) {
		SpringApplication.run(Cardatabase4Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("일" , "김");
		Owner owner2 = new Owner("이","김");

		ownerRepository.saveAll(Arrays.asList(owner1,owner2));

		carRepository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000,owner1));
		carRepository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000,owner2));
		carRepository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000,owner2));

		appUserRepository.save(new AppUser("user","$2a$12$GidqgsnKBA3JU/pF/bQPMulSojc0pRRf1Llz4AYDufbGVU84EVrN6","USER"));
		appUserRepository.save(new AppUser("admin","$2a$12$vS6iFqnpBuC0P4C7R83ALeB211WhQs5pd4yxSFEMiE1k0DOuMD2My","ADMIN"));
	}
}
