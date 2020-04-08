/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author david
 */
public class Usuario {
    //Establecemos las propiedades que contendra el Usuario
    private int id;
    private String codigo;
    private String nombre;
    private String telefono;
    private String usuario;
    private String contrasena;
    
    //Creamos una clasecon sus respectivos constructores para poner incializar usuarios
    public Usuario(){
    }

    public Usuario(String codigo, String nombre, String telefono, String usuario, String contrasena) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    //Creamos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }            
}
