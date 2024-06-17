package com.iudigital.concurrency.example;

public class ThreadClass extends Thread {

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, i'm Thread");
        }

    }
}
