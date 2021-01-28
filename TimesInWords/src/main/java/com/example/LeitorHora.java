package com.example;

import java.util.Scanner;

public class LeitorHora {

    private LeitorHora() {
    }

    private static final Scanner leitor = new Scanner(System.in);

    public static String getHora() {
        return leitor.nextLine();
    }
}
