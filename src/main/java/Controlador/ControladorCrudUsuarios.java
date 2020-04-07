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
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnConsultar) {
            listar(vista.jUsuarios);
        }    
        
        if (e.getSource() == vista.btnGrabar) {
            agregar();
        }
    }
    
    public void agregar(){
        usuario.setCodigo(vista.txtDNI.getText());
        usuario.setNombre(vista.txtNombre.getText());
        usuario.setTelefono(vista.txtTelefono.getText());
        usuario.setUsuario(vista.txtUsuario.getText());
        usuario.setContrasena(vista.txtContrasena.getText());
        
        boolean agregado = dataAcess.crear(usuario);
        
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
}
