package com.company;

import com.company.base.Pokemon;
import com.company.base.Pokemon.Tipo;
import com.company.ui.Vista;
import com.company.util.Util;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import static com.company.Controlador.Origen.*;

public class Controlador implements MouseListener {

    public enum Origen {
        nuevo, editar
    }

    private Vista vista;
    private Modelo modelo;
    private File ficheroSeleccionado;
    private Origen origen;


    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        vista.tfNombre.requestFocus();
        addListeners();
        poblarTiposPokemon();
        modoEdicion(false);
        modelo.getPokemones().forEach(vista.mPokemons::addElement);
    }

    public void addListeners() {
        // Boton "nuevo"
        vista.btNuevo.addActionListener(e -> {
            vaciarCajas();
            origen = nuevo;
            modoEdicion(true);
        });

        // Boton "editar"
        vista.btEditar.addActionListener(e -> {
            origen = editar;
            vista.tfNombre.selectAll();
            vista.tfNombre.requestFocus();
            modoEdicion(true);
        });

        // Boton "cancelar"
        vista.btCancelar.addActionListener(e -> {
            origen = null;
            vaciarCajas();
            modoEdicion(false);
        });

        // Boton "guardar"
        vista.btGuardar.addActionListener(e -> {
            // Si venimos de nuevo y ya existe el pokemon, no lo guarda
            if (origen == nuevo && modelo.pokemones.containsKey(vista.tfNombre.getText())){
                JOptionPane.showMessageDialog(vista, "Ya existe un Pokemon con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
                vista.tfNombre.selectAll();
                vista.tfNombre.requestFocus();
                return;
            }
            // Si venimos origen editar, borra el original antes de guardar
            if (origen == editar)
                modelo.pokemones.remove(vista.lPokemon.getSelectedValue().getNombre());

            // Guardar y restablecer
            guardarPokemon();
            refrescarLista();
            modoEdicion(false);
        });

        // Boton "eliminar"
        vista.btEliminar.addActionListener(e -> {
            // Borrar del modelo y de la lista
            int indice = vista.lPokemon.getSelectedIndex();
            modelo.pokemones.remove(vista.lPokemon.getSelectedValue().getNombre());
            vista.mPokemons.remove(indice);

            // Guardar los cambios
            try {
                modelo.guardarEnDisco();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            // Que seleccione al que estaba antes en la lista
            vista.lPokemon.setSelectedIndex(indice > 0 ? indice - 1: 0);
            modoEdicion(false);
        });

        // Click en la foto
        vista.lFoto.addMouseListener(this);

        // Click en la lista
        vista.lPokemon.addListSelectionListener(lse -> {
            if (vista.lPokemon.getSelectedValue()!=null) {
                cargarPokemonSeleccionado(vista.lPokemon.getSelectedValue());
                vista.btEliminar.setEnabled(true);
                vista.btEditar.setEnabled(true);
            }
        });
    }

    public void guardarPokemon(){
        // Comprobacion de datos
        if (vista.tfNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (vista.tfNivel.getText().equals(""))
            vista.tfNivel.setText("0");
        if (!vista.tfNivel.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "El nivel debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            vista.tfNivel.selectAll();
            vista.tfNivel.requestFocus();
            return;
        }
        if (vista.tfPeso.getText().equals(""))
            vista.tfPeso.setText("0");
        if (!vista.tfNivel.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "El peso debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            vista.tfNivel.selectAll();
            vista.tfNivel.requestFocus();
            return;
        }

        // Recoger los datos de las cajas y vaciarlas
        String nombre = vista.tfNombre.getText();
        Tipo tipo = (Tipo)vista.cbTipo.getSelectedItem();
        int nivel = Integer.parseInt(vista.tfNivel.getText());
        float peso = Float.parseFloat(vista.tfPeso.getText());
        vaciarCajas();

        // Guardar el pokemon
        String nombreImagen = ficheroSeleccionado != null ? ficheroSeleccionado.getName() : "imagenes/pokemon.jpg";
        Pokemon nuevoPokemon = new Pokemon(nombre, tipo, nivel, peso, nombreImagen);

        try {
            modelo.guardarPokemon(nuevoPokemon);
            vista.mPokemons.addElement(nuevoPokemon);
            if (ficheroSeleccionado != null) Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(), nombreImagen);
            vista.lFoto.setIcon(null);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void cargarPokemonSeleccionado(Pokemon pokemonSeleccionado){
        vista.tfNombre.setText(pokemonSeleccionado.getNombre());
        vista.tfPeso.setText(String.valueOf(pokemonSeleccionado.getPeso()));
        vista.tfNivel.setText(String.valueOf(pokemonSeleccionado.getNivel()));
        vista.cbTipo.setSelectedItem(pokemonSeleccionado.getTipo());
    }

    public void vaciarCajas(){
        vista.tfNombre.setText("");
        vista.tfNombre.requestFocus();
        vista.cbTipo.setSelectedItem(Tipo.PLANTA);
        vista.tfNivel.setText("");
        vista.tfPeso.setText("");
    }

    public void refrescarLista(){
        vista.mPokemons.removeAllElements();
        modelo.getPokemones().forEach(vista.mPokemons::addElement);
    }

    public void poblarTiposPokemon(){
        for (Tipo tipo: Tipo.values()) vista.cbTipo.addItem(tipo);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return;

        ficheroSeleccionado = jfc.getSelectedFile();
        vista.lFoto.setIcon(new ImageIcon(ficheroSeleccionado.getPath()));
    }

    public void modoEdicion(boolean activo){
        // Cajas de texto
        vista.tfNivel.setEditable(activo);
        vista.tfNombre.setEditable(activo);
        vista.tfPeso.setEditable(activo);

        // Botones
        if (activo){
            vista.btNuevo.setEnabled(false);
            vista.btGuardar.setEnabled(true);
            vista.btCancelar.setEnabled(true);
        }
        else {
            vista.btNuevo.setEnabled(true);
            vista.btGuardar.setEnabled(false);
            vista.btCancelar.setEnabled(false);
        }
        vista.btEditar.setEnabled(false);
        vista.btEliminar.setEnabled(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
