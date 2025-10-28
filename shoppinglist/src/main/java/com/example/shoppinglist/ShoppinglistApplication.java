package com.example.shoppinglist;

import com.example.shoppinglist.entity.AppUser;
import com.example.shoppinglist.entity.Item;
import com.example.shoppinglist.repository.AppUserRepository;
import com.example.shoppinglist.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShoppinglistApplication implements CommandLineRunner {
	private final ItemRepository itemRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ShoppinglistApplication(ItemRepository itemRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.itemRepository = itemRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);

	}

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser1 = new AppUser("user",passwordEncoder.encode("user"));
        appUserRepository.save(appUser1);
        Item item1 = new Item("사과","2개",appUser1);
        itemRepository.save(item1);
        System.out.println(appUser1.getPassword());

    }
}
