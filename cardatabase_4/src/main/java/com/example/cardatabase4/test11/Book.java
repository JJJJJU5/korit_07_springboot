package com.example.cardatabase4.test11;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private String title , author;



    public void displayInfo(){
        System.out.print("제목: " + getTitle());
        System.out.print(", 저자: "+ getAuthor());
    }
}
