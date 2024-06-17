package com.iudigital.concurrency.example;

public class MainThread {

    public static void main(String[] args) {
        ThreadClass tc = new ThreadClass();
        tc.start();
        System.out.println("Thread Main, processing");
        System.out.println("Finish, Main thread");
        System.out.println("===========================");
    }
}
