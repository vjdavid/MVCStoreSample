/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;
import Modelo.Usuario;
import java.util.List;

/**
 *
 * @author david
 */
public interface IUsuarioDAO {
    
    public boolean crear(Usuario tusuario);
    public List<Usuario> obtener();
    public boolean actualizar(Usuario tusuario);
    public boolean eliminar(String codigo);
    
}


