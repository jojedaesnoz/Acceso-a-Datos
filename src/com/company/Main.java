package com.company;

import com.company.ui.Vista;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Vista vista = new Vista();
            Modelo modelo = new Modelo();
            Controlador controlador = new Controlador(vista, modelo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
