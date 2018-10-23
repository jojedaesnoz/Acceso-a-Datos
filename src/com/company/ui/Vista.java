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
    public JButton btAddPokemon;
    public JLabel lFoto;
    public JScrollPane scrollPane;
    public JList<Pokemon> lPokemon;
    public DefaultListModel<Pokemon> mPokemons;

    private final String[] nombresLabel = {
            "Nombre*:",
            "Tipo:",
            "Nivel:",
            "Peso:",
            "Añadir Pokemon"
    };

    public Vista(){
        tfNombre = new JTextField();
        cbTipo = new JComboBox<>();
        tfNivel = new JTextField();
        tfPeso = new JTextField();
        btAddPokemon = new JButton();

        prepararVentana();
        ponerComponentes();
    }

    private void prepararVentana(){
        setVisible(true);
        setSize(500, 200);
        setLayout(new GridLayout(1, 4, 20, 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void ponerComponentes(){
        JComponent[] components = new JComponent[]{tfNombre, cbTipo, tfNivel, tfPeso};
        JPanel panelEtiquetas = new JPanel(new GridLayout(nombresLabel.length, 1));
        JPanel panelEntradas = new JPanel(new GridLayout(nombresLabel.length, 1));
        int i;

        for (i = 0; i < components.length; i++) {
            JLabel label = new JLabel(nombresLabel[i], SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(120, 30));
            panelEtiquetas.add(label);
            components[i].setPreferredSize(new Dimension(200, 30));
            panelEntradas.add(components[i]);
        }
        panelEntradas.add(btAddPokemon);

        btAddPokemon.setText(nombresLabel[i]);
        add(panelEtiquetas);
        add(panelEntradas);
        ponerFoto();
        ponerJList();
    }

    private void ponerJList(){
        mPokemons = new DefaultListModel<>();
        lPokemon = new JList<>(mPokemons);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lPokemon);
        add(scrollPane);
    }

    private void ponerFoto(){
        lFoto = new JLabel();
        lFoto.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2, true),
                "Imagen", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        lFoto.setIcon(new ImageIcon("C:\\Users\\AlumnoT\\IdeaProjects\\Pokemon\\src\\com\\company\\ui\\pokemon.jpg"));
        add(lFoto);
    }
}
