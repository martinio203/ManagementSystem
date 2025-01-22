package org.example;

public class Main {
    public static void main(String[] args) {

        StringBuilder numbin = new StringBuilder();
        int num = 2;

        while (num > 0) {
            numbin.append(num % 2);
            num /= 2;
        }

        System.out.println(numbin);
    }
}