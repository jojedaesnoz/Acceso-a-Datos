package com.company.login;

import com.company.Util;
import com.company.ui.Vista;

import java.sql.SQLException;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

    /*
    TODO: poner que cuando falle el login te informe del error, borre la contraseña y
    seleccione el texto + request focus.
     */
        try {
            modelo.conectar();
            iniciarSesion();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void iniciarSesion() {
        boolean autenticado = false;
        Login login = new Login();
        int intentos = 0;

        do {
            login.pfContrasena.setText("");
            login.tfUsuario.selectAll();
            login.tfUsuario.requestFocus();
            login.mostrarDialogo();

            String usuario = login.getUsuario();
            String contrasena = login.getContrasena();

            try {
                autenticado = modelo.iniciarSesion(usuario, contrasena);
                if (!autenticado) {
                    if (intentos > 2) {
                        Util.mensajeError("Número de intentos superado.");
                        System.exit(0);
                    }

                    login.mostrarMensaje("Error en el usuario y/o contraseña.");
                    intentos++;
                    continue;
                }

                // CODIGO EXTRA
                System.out.println("Has entrado con usuario:" + usuario + " y contraseña:" + contrasena);

            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } while(!autenticado);
    }

    private void ponerListeners(Login login) {

    }
}
