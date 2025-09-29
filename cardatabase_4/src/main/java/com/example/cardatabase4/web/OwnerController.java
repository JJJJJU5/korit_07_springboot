package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public List<Owner> findByAll() {
        return ownerService.findByAll();
    }
    @PostMapping("/owners")
    public ResponseEntity<Owner> addowner(@RequestBody Owner owner){
        Owner saveowner = ownerService.addonwer(owner);
        return new ResponseEntity<>(saveowner, HttpStatus.CREATED);
    }
    @GetMapping("/owners/{id}")
    public Optional<Owner>findById(@PathVariable Long id){
        return ownerService.getById(id);
    }
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Owner>deleteowner(@PathVariable Long id) {
        if (ownerService.deleteOwner(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateowner (@PathVariable Long id , @RequestBody Owner ownerDetails){
       return ownerService.updateowner(id,ownerDetails)
               .map(owner -> ResponseEntity.ok().body(owner))
               .orElse(ResponseEntity.notFound().build());
    }
}
