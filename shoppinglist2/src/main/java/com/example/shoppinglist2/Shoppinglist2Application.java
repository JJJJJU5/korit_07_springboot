package com.example.shoppinglist2;

import com.example.shoppinglist2.domain.ShoppingItem;
import com.example.shoppinglist2.domain.ShoppingItemRepository;
import com.example.shoppinglist2.domain.User;
import com.example.shoppinglist2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class Shoppinglist2Application implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ShoppingItemRepository shoppingItemRepository;
	private  final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
		SpringApplication.run(Shoppinglist2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user= new User("user", passwordEncoder.encode("user"), "User");
		User admin = new User("admin",passwordEncoder.encode("admin"),"Admin");
		userRepository.save(user);
		userRepository.save(admin);
		ShoppingItem shoppingItem1 = new ShoppingItem("사과","5개",false,user);
		ShoppingItem shoppingItem2 = new ShoppingItem("배","4개",true,user);
		ShoppingItem shoppingItem3 = new ShoppingItem("바나나","3개",false,admin);
		ShoppingItem shoppingItem4 = new ShoppingItem("수박","2개",true,admin);
		shoppingItemRepository.save(shoppingItem1);
		shoppingItemRepository.save(shoppingItem2);
		shoppingItemRepository.save(shoppingItem3);
		shoppingItemRepository.save(shoppingItem4);



	}
}
