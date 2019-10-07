/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.MensajeDAO;
import DAO.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.BandejaEntrada;
import vista.Login;
import vista.NuevoMensaje;

/**
 *
 * @author beatr
 */
public class MensajeController implements ActionListener{

    private NuevoMensaje v;
    public MensajeController(NuevoMensaje v) {
        this.v= v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando == "nuevo mensaje") {
            String remitente = v.getDestinatarioCB().getSelectedItem.toString();
            String contenido = v.getCuerpoMensajeTA().getText();

            Mensaje m = new Mensaje(emisor, remitente, contenido);

            boolean b = MensajeDAO.insertarMensaje(m);

        }
    }
}
        
    
        
     
    