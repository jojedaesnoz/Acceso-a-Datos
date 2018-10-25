package com.company.ui;

import com.company.base.Pokemon;
import static com.company.base.Pokemon.Tipo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Vista extends JFrame {
    public JTextField tfNombre;
    public JComboBox<Tipo> cbTipo;
    public JTextField tfNivel;
    public JTextField tfPeso;
    public JButton btNuevo, btGuardar, btEditar, btCancelar, btEliminar;
    public JLabel lFoto;
    public JScrollPane scrollPane;
    public JList<Pokemon> lPokemon;
    public DefaultListModel<Pokemon> mPokemons;

    public Vista(){

        prepararVentana();
        ponerComponentes();
    }

    private void prepararVentana(){
        setVisible(true);
        setSize(500, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void ponerComponentes(){
        int filas = 4;
        // Etiquetas
        JLabel lNombre = new JLabel("Nombre", SwingConstants.CENTER);
        JLabel lTipo = new JLabel("Tipo", SwingConstants.CENTER);
        JLabel lNivel = new JLabel("Nivel", SwingConstants.CENTER);
        JLabel lPeso = new JLabel("Peso", SwingConstants.CENTER);
        JPanel panelLabels = new JPanel(new GridLayout(filas, 1));
        for (JLabel label: new JLabel[]{lNombre, lTipo, lNivel, lPeso}) {
            label.setPreferredSize(new Dimension(120, 30));
            panelLabels.add(label);
        }

        // Entradas
        tfNombre = new JTextField();
        cbTipo = new JComboBox<>();
        tfNivel = new JTextField();
        tfPeso = new JTextField();
        JPanel panelEntradas = new JPanel(new GridLayout(filas, 1));
        for (JComponent entrada: new JComponent[]{tfNombre, cbTipo, tfNivel, tfPeso}) {
            entrada.setPreferredSize(new Dimension(200, 30));
            panelEntradas.add(entrada);
        }

        // Botones
        btNuevo = new JButton("Nuevo");
        btEditar = new JButton("Modificar");
        btGuardar = new JButton("Guardar");
        btCancelar = new JButton("Cancelar");
        btEliminar = new JButton("Eliminar");
        JPanel panelBotones = new JPanel(new GridLayout(1, 5));
        for (JComponent boton: new JComponent[]{btNuevo, btEditar, btGuardar, btCancelar, btEliminar}){
            panelBotones.add(boton);
        }

        // Poner todos los componentes
        JPanel contenedorCentral = new JPanel();
        contenedorCentral.setLayout(new GridLayout(1, 4));
        for (JComponent component: new JComponent[]{panelLabels, panelEntradas, componenteFoto(), componenteLista()}){
            contenedorCentral.add(component);
        }
        add(contenedorCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JComponent componenteLista(){
        mPokemons = new DefaultListModel<>();
        lPokemon = new JList<>(mPokemons);
        lPokemon.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lPokemon);
        return scrollPane;
    }

    private JComponent componenteFoto(){
        lFoto = new JLabel();
        lFoto.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2, true),
                "Imagen", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        lFoto.setIcon(new ImageIcon("C:\\Users\\AlumnoT\\IdeaProjects\\Pokemon\\src\\com\\company\\ui\\pokemon.jpg"));
        return lFoto;
    }
}
