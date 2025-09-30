package com.example.cardatabase4.test11;

public class main {
    public static void main(String[] args) {
        Book book = new Book("자바의 정석", "남궁성");
        EBook eBook = new EBook("스프링 부트3 백과사전", "김영한","20.5MB");

        book.displayInfo();
        eBook.displayInfo();

    }
}
