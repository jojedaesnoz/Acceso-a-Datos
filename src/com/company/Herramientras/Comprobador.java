package com.company.Herramientras;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Comprobador<T> {
    private ArrayList<Comprobacion<T>> comprobaciones;

    public Comprobador() {
        comprobaciones = new ArrayList<>();
    }

    public Comprobador(ArrayList<Comprobacion<T>> comprobaciones){
        comprobaciones = new ArrayList<>(comprobaciones);
    }

    public void nuevaComprobacion(T dato, Predicate<T> prueba, Consumer<T> accion){
        comprobaciones.add(new Comprobacion<>(prueba, accion));
    }

    public void comprobar(){
        for (Comprobacion c: comprobaciones){

        }
    }

    public class Comprobacion<T> {
        private Predicate<T> prueba;
        private Consumer<T> accion;

        public Comprobacion(Predicate<T> prueba, Consumer<T> accion) {
            this.prueba = prueba;
            this.accion = accion;
        }

        public Predicate<T> getPrueba() {
            return prueba;
        }

        public void setPrueba(Predicate<T> prueba) {
            this.prueba = prueba;
        }

        public Consumer<T> getAccion() {
            return accion;
        }

        public void setAccion(Consumer<T> accion) {
            this.accion = accion;
        }
    }
}
