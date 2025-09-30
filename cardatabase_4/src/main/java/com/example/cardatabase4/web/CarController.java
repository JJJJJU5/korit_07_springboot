package com.example.cardatabase4.web;

import com.example.cardatabase4.entity.Car;
import com.example.cardatabase4.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;
    // 1. 모든 자동차 정보 조회(Get / api/cars)
    @GetMapping("/cars")
    public List<Car> getCars(){
        return carService.findAllCars();
    }
    //2. 차량 한 대 추가 (POST /api/cars)
    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    //3. 차량 한 대 조회
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> GetById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(car -> ResponseEntity.ok().body(car)) // 차량 정보가 있으면 200 Ok를 반환
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carService.deleteCar(id)) {
            return ResponseEntity.noContent().build(); // 삭제 성공시 204 No Content를 반환
        } else {
            return ResponseEntity.notFound().build(); // 대상이 없으면 404 Not Found
        }
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        return carService.updateCar(id,carDetails)
                .map(updateCar -> ResponseEntity.ok().body(updateCar))
                .orElse(ResponseEntity.notFound().build());
    }
}
