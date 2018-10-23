package com.company.util;


import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestOperator<T> {
    public static <T> void operar(Iterable<T> datos, Predicate<T> condicion, Consumer<T> accion){
        for(T dato: datos)
            if (condicion.test(dato))
                accion.accept(dato);
    }

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

    public static <T> boolean check(Iterable<T> data, Predicate<T> test, Consumer<T> action){
        return check(data.iterator(), test, action);
    }

    public static <T> boolean check(T[] data, Predicate<T> test, Consumer<T> action){
        return check(Arrays.asList(data).iterator(), test, action);
    }


//    public static <T> boolean check(T dato, Predicate<T> test, Consumer<T> action){
//        if (test.test(dato)){
//            action.accept(dato);
//            return false;
//        }
//        return true;
//    }

    private static <T> boolean check(Iterator<T> iterator, Predicate<T> test, Consumer<T> action){
        boolean correcto = true;

        // Recorre el iterador hasta que encuentra un error
        while (iterator.hasNext() && correcto){
            T dato = iterator.next();
            if (test.test(dato)){
                action.accept(dato);
                correcto = false;
            }
        }

        // Luego continua si quedan datos sin recorrer
        while (iterator.hasNext()){
            T dato = iterator.next();
            if (test.test(dato)){
                action.accept(dato);
                correcto = false;
            }
        }

        return correcto;
    }
}
