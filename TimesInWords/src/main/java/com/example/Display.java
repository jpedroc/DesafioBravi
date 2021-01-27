package com.example;

public class Display {


    public Display() {
    }

    public static void main(String[] args) {
        HoraService horaService = new HoraService();
        while(true) {
            String horaCompleta = LeitorHora.getHora();
            System.out.println(horaService.converterParaExtenso(horaCompleta));
        }
    }
}
