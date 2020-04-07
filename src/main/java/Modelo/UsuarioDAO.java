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
    
    @Override
    public boolean crear(Usuario usuario) {
        boolean registrar = false;

        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO usuarios(codigo, nombre, telefono, usuario, contrasena) VALUES ('" + usuario.getCodigo() + "','" + usuario.getNombre() + "','" + usuario.getTelefono() + "','" + usuario.getUsuario() + "','" + usuario.getContrasena() + "')";

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
        return registrar;    
    }

    @Override
    public List<Usuario> obtener() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM usuarios ORDER BY nombre";

        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            co = Conexion.conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
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
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        Connection connect = null;
        Statement stm = null;

        boolean actualizar = false;

        String sql = "UPDATE usuarios SET nombre='" + usuario.getNombre() + "', telefono='" + usuario.getTelefono() + "', usuario='" + usuario.getUsuario() + "'" + "', contrasena='" + usuario.getContrasena() + "'" + " WHERE id=" + usuario.getId();
        try {
            connect = Conexion.conectar();
            stm = connect.createStatement();
            stm.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;       
    }

    @Override
    public boolean eliminar(Usuario usuario) {
        Connection connect = null;
        Statement stm = null;

        boolean eliminar = false;

        String sql = "DELETE FROM usuarios WHERE codigo=" + usuario.getCodigo();
        try {
            connect = Conexion.conectar();
            stm = connect.createStatement();
            stm.execute(sql);
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }
}