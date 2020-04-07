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
    
    UsuarioDAO dataAcess = new UsuarioDAO();
    Usuario usuario = new Usuario();
    JFCrudUsuarios vista = new JFCrudUsuarios();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorCrudUsuarios(JFCrudUsuarios vista){
        this.vista = vista;
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnGrabar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }
    
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
    
    public void actualizar() {
        Usuario usuario = llenadoDeDatos();
        
        boolean actualizado = dataAcess.actualizar(usuario);
        
        if(actualizado) {
            JOptionPane.showMessageDialog(vista, "El usuario se actualizo correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar usuario");
        }        
    }
     
    public void agregar(){              
        boolean agregado = dataAcess.crear(llenadoDeDatos());
        
        if (agregado) {
            JOptionPane.showMessageDialog(vista, "El usuario se agrego con exito");            
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
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
    
    public void eliminar(){
        if(vista.txtDNI.getText() != null) {            
            dataAcess.eliminar(vista.txtDNI.getText());
        } else {
            JOptionPane.showMessageDialog(null, "No selecciono fila");
        }
        
    }
    
    public Usuario llenadoDeDatos(){
        usuario.setCodigo(vista.txtDNI.getText());
        usuario.setNombre(vista.txtNombre.getText());
        usuario.setTelefono(vista.txtTelefono.getText());
        usuario.setUsuario(vista.txtUsuario.getText());
        usuario.setContrasena(vista.txtContrasena.getText());
        
        return usuario;
    }
    
    public void limpiarTabla(){
        for(int i = 0; i < vista.jUsuarios.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiarFormulario(){
        vista.txtDNI.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
    }
}
