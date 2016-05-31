/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.driver.OracleDriver;

/**
 * Clase que sirve para trabajar con una Base de Datos de oracle.
 *
 * @author Marcos Pineda Burriel
 * @version 29/05/2016
 */
public class DataBase {

    String bd;
    String login;
    String password;
    String server;
    Connection conexion;

    /**
     * Método constructor de la clase DataBase que construye la Base de Datos.
     *
     * @param bd El nombre de la Base de Datos.
     * @param login El nombre de usuario de la Base de Datos.
     * @param password La contraseña de la Base de Datos.
     * @param server El servidor de la Base de Datos.
     */
    public DataBase(String bd, String login, String password, String server) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.server = server;
    }

    /**
     * Metodo que guarda los datos recuperados de la Base de Datos en un Array
     * de casos de corrupción.
     *
     * @param rs Una sentencia SQL.
     * @param casos El array de casos de corrupción.
     */
    public void creaArrayCasos(ResultSet rs, ArrayList casos) {
        try {
            while (rs.next()) {
//                Caso c = new Caso(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
//                casos.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("SQL exception: " + ex.getMessage());
        }
    }

    /**
     * Método que crea y guarda los datos recuperados de la base de datos en un
     * array de ciudadanos.
     *
     * @param rs Una sentencia SQL
     * @param ciudadanos El array de ciudadanos relacionados con los casos de
     * corrupción.
     */
    public void creaArrayCiudadanos(ResultSet rs, ArrayList ciudadanos) {
        try {
            while (rs.next()) {
//                Ciudadano c = new Ciudadano(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
//                ciudadanos.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("SQL exception: " + ex.getMessage());
        }
    }

    /**
     * Método que sirve para establecer conexión con la Base de Datos.
     *
     * @return Booleano que indica si se ha podido establecer la conexión
     * correctamente o no;
     */
    public boolean connect() {
        boolean success = false;
        try {
            DriverManager.registerDriver(new OracleDriver());
            conexion = DriverManager.getConnection(server + bd, login, password);
            success = true;
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("SQL Exception:\n" + e.toString());
        } catch (Exception e) {
            System.out.println("Exception:\n" + e.toString());
        }

        return success;
    }

    /**
     * Método que cierra la conexion con la base de datos.
     */
    public void disconnect() {

        try {
            conexion.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método de la clase DataBase que ejecuta la Consulta en la Base de Datos.
     *
     * @param consulta La Consulta a ejecutar en la Base de Datos.
     * @return El resultado de la Consulta.
     */
    public ResultSet Consulta(String consulta) {
        Statement st;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("SQL exception: " + ex.getMessage());
        }
        return rs;
    }

    /**
     * Metodo de la clase DataBase
     *
     * @param rs Una sentencia SQl.
     */
    public void recorreResultado(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println("SQL exception: " + ex.getMessage());
        }
    }

    /**
     * Método que ejecuta una sentencia SQL previamente proporcionada en la base
     * de datos.
     *
     * @param statement La sentencia para la Base de Datos.
     * @return El tipo de operacion que se ha llevado a cabo.
     */
    public int Update(String statement) {
        int x = 0;
        try {
            Statement st = conexion.createStatement();
            System.out.println("Sentencia SQL: " + statement);
            x = st.executeUpdate(statement);
            System.out.println("La sentencia se ha ejecutado correctamente.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex.getMessage());
        }
        return x;
    }

    /**
     * Método de la clase DataBase para buscar en la Base de Datos.
     *
     * @param idCaso El id de uno de los casos de corrupción.
     * @return Booleano que indica el resultado de la Consulta.
     */
    public boolean buscaRegistro(String idCaso) {
        ResultSet rs;
        PreparedStatement st;
        String sentencia = "SELECT * from casos_corrupcion where id_caso= ?";
        try {
            st = conexion.prepareCall(sentencia);
            st.setString(1, idCaso);
            rs = st.executeQuery();
            return !rs.isBeforeFirst();
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true;
    }
}
