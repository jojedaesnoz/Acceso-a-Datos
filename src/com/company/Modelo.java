package com.company;

import com.company.base.Pokemon;
import com.company.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Modelo {

    private final String RUTA_POKEMONES = "Pokemones.dat";
    public HashMap<String, Pokemon> pokemones;

    public Modelo() throws IOException, ClassNotFoundException{
        pokemones = new File (RUTA_POKEMONES).exists()? Util.deserialize(RUTA_POKEMONES) : new HashMap<>();
    }

    public void guardarPokemon(Pokemon pokemon) throws IOException{
        pokemones.put(pokemon.getNombre(), pokemon);
        guardarEnDisco();
    }

    public void guardarEnDisco() throws IOException{
        Util.serialize(RUTA_POKEMONES, pokemones);
    }

    public void eliminarPokemon(Pokemon pokemon){
        pokemones.remove(pokemon.getNombre());
    }

    public void modificarPokemon(String nombre, Pokemon pokemon){

    }
//    public Pokemon getPokemon(String busqueda){
//
//    }



    public ArrayList<Pokemon> getPokemones(){
        return new ArrayList<>(pokemones.values());
    }

}
