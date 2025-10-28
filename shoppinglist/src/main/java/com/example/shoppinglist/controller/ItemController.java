package com.example.shoppinglist.controller;

import com.example.shoppinglist.dto.AddItem;
import com.example.shoppinglist.entity.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.shoppinglist.service.ItemService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

//    @GetMapping("/items")
//    public  ResponseEntity<List<Item>> getMyItems(@AuthenticationPrincipal UserDetails userDetails) {
//        return ResponseEntity.ok(itemService.findMyItem(userDetails));
//    }
//
//    @PostMapping("/items")
//    public ResponseEntity<Item> addItem(@RequestBody AddItem addItem , @AuthenticationPrincipal UserDetails userDetails) {
//
//        return ResponseEntity.ok(itemService.addItem(addItem,userDetails));
//    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Item> delItem(@PathVariable Long id ,  @AuthenticationPrincipal UserDetails userDetails){
        itemService.delItem(id,userDetails);
        return ResponseEntity.noContent().build();
    }

}
