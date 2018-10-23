package com.company.util;


import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Operator<T> {
    public static <T> void operar(Iterable<T> datos, Predicate<T> condicion, Consumer<T> accion){
        for(T dato: datos)
            if (condicion.test(dato))
                accion.accept(dato);
    }

    /**
     * Carga datos desde una ruta introducida
     * @param ruta
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deserialize(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream deserializador = new ObjectInputStream(new FileInputStream(ruta));
        T datos = (T) deserializador.readObject();
        deserializador.close();
        return datos;
    }

    public static <T> void serialize(String ruta, T objeto) throws IOException{
        ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream(ruta));
        serializador.writeObject(objeto);
        serializador.close();
    }
}
