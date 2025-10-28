package com.example.shoppinglist.service;

import com.example.shoppinglist.dto.AddItem;
import com.example.shoppinglist.entity.AppUser;
import com.example.shoppinglist.entity.Item;
import com.example.shoppinglist.repository.AppUserRepository;
import com.example.shoppinglist.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final AppUserRepository appUserRepository;

//    public Item addItem(AddItem addItem, UserDetails userDetails) {
//        AppUser appUser = appUserRepository.findByUsername(userDetails.getUsername()).get();
//        Item items = new Item(addItem.product(), addItem.amount(), appUser);
//        return itemRepository.save(items);
//    }
//
//    public List<Item> findMyItem(UserDetails userDetails) {
//        List<Item> asd = itemRepository.findAll();
//        AppUser appUser = appUserRepository.findByUsername(userDetails.getUsername()).get();
//        return asd.stream().filter(item -> Objects.equals(item.getAppUser().getUser_id(), appUser.getUser_id())).toList();
//    }

    public void delItem(Long id , UserDetails userDetails){
        Item itemDel = itemRepository.findById(id).orElseThrow(()-> new RuntimeException(""));
        if(!itemDel.getAppUser().getUsername().equals(userDetails.getUsername())){
            throw new UsernameNotFoundException("");
        }
        itemRepository.delete(itemDel);
    }
    public Item updateItem(Long id ,UserDetails userDetails) {
        return null;
    }


}
