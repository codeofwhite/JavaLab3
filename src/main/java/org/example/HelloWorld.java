package org.example;

public class HelloWorld {
    public int age = 10;
    public String name = "mom";
    HelloWorld(int age, String name){
        this.age = age;
        this.name = name;
    }
    public void outcry(){
        System.out.println(name + " is " + age);
    }
}
