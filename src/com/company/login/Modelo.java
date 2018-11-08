package com.company.login;


import java.sql.*;

public class Modelo {

    // Todo: ponerlo en un fichero properties
    private final String IP = "192.168.34.5";
    private final String BASE_DATOS = "jojeda";
    private final String USUARIO = "jojeda";
    private final String CONTRASENA = "s5tzka";
    private Connection conexion;


    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(
                "jdbc:mysql://" + IP + ":3306/" + BASE_DATOS, USUARIO, CONTRASENA);
    }

    /*
    Los permisos de usuario y demás se verifican en la aplicación, NO EN LA BASE DE DATOS
     */

    private void cargarConfiguracion() {

    }

    public void desconectar() throws SQLException {
        conexion.close();
        conexion = null;
    }


    public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {
        String sql = "select id from usuarios where usuario = ? " +
                "AND contrasena = SHA1(?);";

        // Parametrizar la consulta para evitar inyeccion de codigo
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, usuario);
        sentencia.setString(2, contrasena);

        ResultSet resultado = sentencia.executeQuery();

        return resultado.next();
    }

    public void crearTablaUsuarios() throws SQLException{
        String sql = "CREATE TABLE USUARIOS ("
                + "id integer unsigned auto_increment primary key,"
                + "usuario varchar(20),"
                + "contrasena varchar(20));";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.execute();
    }

    public void insertarUsuario() throws SQLException{
        String sql = "Insert into USUARIOS(usuario, contrasena) values('asd', 'asd')";
        conexion.prepareStatement(sql).execute();
    }
}
