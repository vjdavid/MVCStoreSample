/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
/**
 *
 * @author david
 */
public class Conexion {
    
    //Creamos una clase con un metodo static para poder mandarlo a llamar en cualquier dataAccess
    public static Connection conectar() {
        Connection conectar = null;
        
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tienda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "david12345");            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conectar;
    }
}