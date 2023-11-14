package com.example;

public class dog extends animal{

    String name="dog";
    public  void zoo(){
        System.out.println(super.name);
        System.out.println("dog zoo");
        super.zoo();
    }

    public static void main(String[] args) {
        animal dog=new dog();
        dog.zoo();
    }
}
