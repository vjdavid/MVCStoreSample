/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.UsuarioDAO;
import Modelo.Usuario;
import Vistas.JFCrudUsuarios;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class ControladorCrudUsuarios implements ActionListener {
    
    //Creamos los objetos para cada una de las diferentes capas
    UsuarioDAO dataAcess = new UsuarioDAO();
    Usuario usuario = new Usuario();
    JFCrudUsuarios vista = new JFCrudUsuarios();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    /* Creamos un constructor para inicializar todos los elementos
     al momento de instanciar el controlador */
    public ControladorCrudUsuarios(JFCrudUsuarios vista){
        this.vista = vista;
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnGrabar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }
    
    /*Sobre escribimos el metodo implementado de ActionListener para 
    realizar diferentes acciones dependiendo lo que el usuario seleccionado en la vista */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnConsultar) {
            limpiarTabla();
            listar(vista.jUsuarios);
        }    
        
        if (e.getSource() == vista.btnGrabar) {
            agregar();
            limpiarTabla();
            listar(vista.jUsuarios);
            limpiarFormulario();
        }
        
        if (e.getSource() == vista.btnModificar) {
            actualizar();
            limpiarTabla();
            listar(vista.jUsuarios);
            limpiarFormulario();
        }
        
        if (e.getSource() == vista.btnEliminar) {
            eliminar();            
            limpiarTabla();
            listar(vista.jUsuarios);
            limpiarFormulario();
        }               
    }
    
    //Creamos un procedimiento para actualizar la instancia del usuario que hayamos seleccionado
    public void actualizar() {
        Usuario usuario = llenadoDeDatos();
        
        boolean actualizado = dataAcess.actualizar(usuario);
        
        if(actualizado) {
            JOptionPane.showMessageDialog(vista, "El usuario se actualizo correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar usuario");
        }        
    }
     
    //Agregemos un metodo para enviar todos los atributos de una instancia al dataAccess para crear el usuario
    public void agregar(){              
        boolean agregado = dataAcess.crear(llenadoDeDatos());
        
        if (agregado) {
            JOptionPane.showMessageDialog(vista, "El usuario se agrego con exito");            
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
    //Creamos un metodo para ejecutarse en el JTable e introducir los usuarios en esta tabla
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Usuario>lista = this.dataAcess.obtener();
        
        Object[]object = new Object[5];
        
        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getCodigo();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getTelefono();
            object[3] = lista.get(i).getUsuario();
            object[4] = lista.get(i).getContrasena();
            modelo.addRow(object);            
        }
        tabla.setModel(modelo);
    }
    
    //Este metodo toma toda la fila seleccionada y setea los valores en cada uno de los inputs
    public void llenadoFormulario(int fila){
        
        if (fila >= 0) {
            vista.txtDNI.setText(vista.jUsuarios.getValueAt(fila, 0).toString());
            vista.txtNombre.setText(vista.jUsuarios.getValueAt(fila, 1).toString());
            vista.txtTelefono.setText(vista.jUsuarios.getValueAt(fila, 2).toString());
            vista.txtUsuario.setText(vista.jUsuarios.getValueAt(fila, 3).toString());
            vista.txtContrasena.setText(vista.jUsuarios.getValueAt(fila, 4).toString());
        } else {
            JOptionPane.showMessageDialog(null, "No selecciono fila");
        }
    }
    
    //Este metodo un id y elimina un registro utilizando el dataAccess
    public void eliminar(){
        if(vista.txtDNI.getText() != null) {            
            dataAcess.eliminar(vista.txtDNI.getText());
        } else {
            JOptionPane.showMessageDialog(null, "No selecciono fila");
        }
        
    }
    
    //Este toma la instancia actual de usuario y setea cada uno de los valores tomando como referencia los inputs
    public Usuario llenadoDeDatos(){
        usuario.setCodigo(vista.txtDNI.getText());
        usuario.setNombre(vista.txtNombre.getText());
        usuario.setTelefono(vista.txtTelefono.getText());
        usuario.setUsuario(vista.txtUsuario.getText());
        usuario.setContrasena(vista.txtContrasena.getText());
        
        return usuario;
    }
    
    //Este metodo elimina los registros que tenga actualmente el JTable
    public void limpiarTabla(){
        for(int i = 0; i < vista.jUsuarios.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    //Este metodo setea todos los inputs a su estado vacio
    public void limpiarFormulario(){
        vista.txtDNI.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
    }
}
