package com.iudigital.concurrency.example;

public class AnonymousClass {
    public static void main(String[] args) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello, I'am Thread");
                }
            }
        });
        hilo.start();
        System.out.println("I'am main thread and success ");
        System.out.println("Finish ");
        System.out.println("=================================");
    }
}
