package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량 저장 메서드")
    void saveCar() {
        // given - 제반 준비 사항
        // Car Entity를 확인했을 때 field로 Owner를 요구하기 때문에
        // 애부터 먼저 만들고 -> ownerRepository에 저장
        Owner owner = new Owner ("Gemini","GPT");
        ownerRepository.save(owner);
        // 그리고 Car 객체를 생성
        Car car = new Car("Ford","Mustang","Red","ABCDEF",2021,567890,owner);
        // when - 테스트 실행
        //저장이 됐는가를 확인하기 위한 부분
        carRepository.save(car);
        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();

        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }
   @Test
   @DisplayName("삭제 테스트")
    void deleteCar() {
        // given -> Car 객체 생성 / 저장
       Owner owner = new Owner();
       ownerRepository.save(owner);
       Car car = new Car();
       carRepository.save(new Car("Ford","Mustang","Red","ABCDEF",2021,567890,owner));
       // when -> 삭제
//       carRepository.deleteAll();
       carRepository.deleteById(car.getId());
       // then -> 삭제가 올바로 되었는지 assertThat() 구문
//       assertThat(carRepository.count()).isEqualTo(0);
       assertThat(carRepository.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("브랜드 자동차 조회")
    void findByBrand() {
        //given - Owner 하나 생성 및 저장 / Car 객체 3 대 생성 및 저장

        Owner owner = new Owner();
        ownerRepository.save(owner);
        Car car = new Car("Ford","Mustang","Red","ABCDEF",2021,567890,owner);
        carRepository.save(car);

        carRepository.save(new Car("Ford","LECE","Red","ABCDEF",2021,567890,owner));
        carRepository.save(new Car("Kia","Mus","Red","ABCDEF",2021,567890,owner));
        //when - carRepository.findByBrand("브랜드명") ->
        List<Car> Fords = carRepository.findByBrand("Ford");

        //then에서의 검증은 list 내부의 element의 자료형이 Car 객체일테니까
        // 그 객체의 getBrand()의 결과값이 우리가 findByBrand()의 argment로 쓴 값과 동일한지 체크 할 수 있다.
        assertThat(Fords.get(0).getBrand()).isEqualTo("Ford");
        // 혹은, Ford 차량을 두 대 만들었으니까 size()의 결과값은 2;
        assertThat(Fords.size()).isEqualTo(2);

    }
}
