package com.example.cardatabase4.service;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.domain.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class OwnerService {
   private final OwnerRepository ownerRepository;


    public List<Owner> findByAll() {
        return ownerRepository.findAll();
    }
    public Owner addonwer (Owner owner){
        return ownerRepository.save(owner);
    }
    public Optional<Owner> getById(Long id){
        return ownerRepository.findById(id);
    }
    public boolean deleteOwner (long id){
        if ( ownerRepository.existsById(id)){
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Transactional
    public Optional<Owner> updateowner (Long id, Owner ownerDetails){
       return ownerRepository.findById(id)
                .map(owner ->  {
                   owner.setFirstName(ownerDetails.getFirstName());
                   owner.setLastName(ownerDetails.getLastName());
                    return owner;
                });
    }
}
