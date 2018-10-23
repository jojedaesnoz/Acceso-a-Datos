package com.company.base;

import java.io.File;
import java.io.Serializable;

public class Pokemon implements Serializable {
    public enum Tipo{
        PLANTA,
        TIERRA,
        AGUA,
        FUEGO
    }

    private String nombre;
    private Tipo tipo;
    private int nivel;
    private float peso;
    private String nombreImagen;

    public Pokemon(String nombre, Tipo tipo, int nivel, float peso, String nombreImagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.peso = peso;
        this.nombreImagen = nombreImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
