package com.company;

import com.company.base.Pokemon;
import com.company.util.Operator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Modelo {

    private final String RUTA_POKEMONES = "Pokemones.dat";
    public HashMap<String, Pokemon> pokemones;

    public Modelo() throws IOException, ClassNotFoundException{
        pokemones = new File (RUTA_POKEMONES).exists()? Operator.deserialize(RUTA_POKEMONES) : new HashMap<>();
    }

    public boolean guardarPokemon(Pokemon pokemon) throws IOException{
        if (!pokemones.containsKey(pokemon.getNombre())){
            pokemones.put(pokemon.getNombre(), pokemon);
            guardarEnDisco();
            return true;
        }
        else return false;
    }

    private void guardarEnDisco() throws IOException{
        Operator.serialize(RUTA_POKEMONES, pokemones);
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
