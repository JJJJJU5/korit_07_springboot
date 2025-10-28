package com.example.shoppinglist.repository;

import com.example.shoppinglist.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository <Item, Long >{

}
