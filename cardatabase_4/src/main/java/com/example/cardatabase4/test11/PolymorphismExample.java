package com.example.cardatabase4.test11;

public class PolymorphismExample {

    class Animal {
        public void makeSound() {
            System.out.println("동물이 소리를 냅니다.");
        }
    }

        class Dog extends Animal {
            @Override
            public void makeSound() {
                System.out.println("개가 짖습니다.");
            }
            public void fetch() {
                System.out.println("멍멍");
            }
        }

        class Cat extends Animal {
            @Override
            public void makeSound() {
                System.out.println("고양이가 소리를 냅니다.");
            }
        }



    public static void main(String[] args) {
        PolymorphismExample example = new PolymorphismExample();

        Animal[] animals = new Animal[2];

        animals[0] = example.new Dog();
        animals[1] = example.new Cat();

        for (int i = 0 ; i < animals.length; i++){
            if(animals[i] instanceof Dog dog){
                dog.fetch();
            } else {
                animals[i].makeSound();
            }
        }

    }
}
