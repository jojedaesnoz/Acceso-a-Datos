package com.company;

import com.company.base.Pokemon;
import com.company.ui.Vista;

import javax.swing.*;

import com.company.base.Pokemon.Tipo;
import com.company.util.Util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Controlador implements MouseListener {

    private Vista vista;
    private Modelo modelo;
    private File ficheroSeleccionado;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        vista.tfNombre.requestFocus();
        addListeners();
        poblarTiposPokemon();
        refrescarLista();
    }
    //TODO aprender el generador de javadoc

    public void addListeners() {
        vista.btAddPokemon.addActionListener(e -> nuevoPokemon());
        vista.lFoto.addMouseListener(this);
    }

    public void nuevoPokemon(){
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
        String nombreImagen = null;
        if (ficheroSeleccionado != null)
            nombreImagen = ficheroSeleccionado.getName();
        else
            nombreImagen = "imagenes/pokemon.jpg";

        // Procedimiento
        String nombre = vista.tfNombre.getText();
        Tipo tipo = (Tipo)vista.cbTipo.getSelectedItem();
        int nivel = Integer.parseInt(vista.tfNivel.getText());
        float peso = Float.parseFloat(vista.tfPeso.getText());

        // Guardar el pokemon
        Pokemon nuevoPokemon = new Pokemon(nombre, tipo, nivel, peso, nombreImagen);
        try {
            if (modelo.guardarPokemon(nuevoPokemon)){
                vista.mPokemons.addElement(nuevoPokemon);
                Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(), nombreImagen);
                vista.lFoto.setIcon(null);
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Vaciar las cajas
        vaciarCajas();
    }

    public void vaciarCajas(){
        vista.tfNombre.setText("");
        vista.tfNombre.requestFocus();
        vista.tfNivel.setText("");
        vista.tfPeso.setText("");
    }

    public void refrescarLista(){
        vista.lPokemon.clearSelection();
        modelo.getPokemones().forEach(vista.mPokemons::addElement);
    }

    public void poblarTiposPokemon(){
        for (Tipo tipo: Tipo.values()){
            vista.cbTipo.addItem(tipo);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return;

        ficheroSeleccionado = jfc.getSelectedFile();
        vista.lFoto.setIcon(new ImageIcon(ficheroSeleccionado.getPath()));
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
