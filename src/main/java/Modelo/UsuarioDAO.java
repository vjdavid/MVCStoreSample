/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Idao.IUsuarioDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class UsuarioDAO implements IUsuarioDAO {
    
    //Creamos la implementacion del metodo para establecer la creacion del metodo
    @Override
    public boolean crear(Usuario usuario) {
        //creamos una variable que dependiento 
        boolean registrar = false;

        Statement stm = null;
        Connection con = null;
        
        //Creamos el query para insertar 
        String sql = "INSERT INTO usuarios(codigo, nombre, telefono, usuario, contrasena) VALUES ('" + usuario.getCodigo() + "','" + usuario.getNombre() + "','" + usuario.getTelefono() + "','" + usuario.getUsuario() + "','" + usuario.getContrasena() + "')";

        //Creamos un try catch para cachar cualquier error que se produzca el momento de ejecutar el query
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método registrar");
            e.printStackTrace();
        }
        //se retorna verdadero si todo fue un exito
        return registrar;    
    }

    //Creamos un metodo que almacenara una lista con todos los usuarios obteneidos
    @Override
    public List<Usuario> obtener() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;

        //Creamos el string de sql para para obtener los usuarios ordenados por nombre
        String sql = "SELECT * FROM usuarios ORDER BY nombre";

        //Creamos la lista vacia para listar los usuarios
        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        //Creamos un try catch para cachar los errores
        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            
            //si nos regresa todos los resultados tomamos todos y los valores y los 
            //almacenamos en la lista
            while (rs.next()) {
                Usuario c = new Usuario();
                c.setId(rs.getInt(1));
                c.setCodigo(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setTelefono(rs.getString(4));
                c.setUsuario(rs.getString(5));
                c.setContrasena(rs.getString(6));
                listaUsuario.add(c);
            }
            stm.close();
            rs.close();
            co.close();
            //cerramos la conexion y todos los comandos ejecutados anteriormente
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método obtener");
            
            //se regresa un error si hubo algun fallo
            e.printStackTrace();
        }

        return listaUsuario;
    }

    //Creamos un metodo para actualizar la instancia del usuarios y mandarla en un sql query
    @Override
    public boolean actualizar(Usuario usuario) {
        Connection connect = null;
        Statement stm = null;

        boolean actualizar = false;

        //se asigna el sql string en base a los valores que se hayan recibido.
        String sql = "UPDATE usuarios SET nombre='" + usuario.getNombre() + "', telefono='" + usuario.getTelefono() + "', usuario='" + usuario.getUsuario() + "', contrasena='" + usuario.getContrasena() + "'" + " WHERE codigo='" + usuario.getCodigo() + "';";
        System.out.println(sql);
        
        //se emplea un try catch para establecer los valores y ejecutar el sql string
        try {
            connect = Conexion.conectar();
            stm = connect.createStatement();
            stm.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método actualizar");
            e.printStackTrace();
        }
        
        //se retorna verdadero si todo fue un exito
        return actualizar;       
    }

    //Se crea un metodo para eliminar cualquier usuario utilizando un query sql
    @Override
    public boolean eliminar(String codigo) {
        Connection connect = null;
        Statement stm = null;

        boolean eliminar = false;

        //Se crea el query sql para eliminar un usuario en especifico
        String sql = "DELETE FROM usuarios WHERE codigo='" + codigo + "';";
        System.out.println(sql);
        try {
            connect = Conexion.conectar();
            stm = connect.createStatement();
            //se ejecuta el query
            stm.execute(sql);
            eliminar = true;
        } catch (SQLException e) {
            //se imprime si hubo algun error 
            System.out.println("Error: Clase UsuarioDaoImple, método eliminar");
            e.printStackTrace();
        }
        
        //se retorna verdadero si todo fue un exito
        return eliminar;
    }
}