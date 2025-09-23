package com.example.cardatabase.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;


public interface CarRepository  extends JpaRepository<Car, Long> {

    @Query("select c from Car c where c.brand = ?1")
    List<Car> findByBrand(String brand);
    // SQL을 활용한 브랜드로 자동차를 검색하는데 부분 검색을 적용
    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrandEndsWith(String brand);

    @Query("select c from Car c where c.brand like ?1%")
    List<Car>findByBrandStartsWith(String brand);
}

