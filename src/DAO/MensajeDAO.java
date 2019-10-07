/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author beatr
 */
public class MensajeDAO {

    Conexion con;

    public MensajeDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Mensaje> getMensajes() {

        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM mensaje ";

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                Usuario emisor = resultados.getEmisor();
                Usuario remitente = resultados.getRemitente();
                String contenido = resultados.getString("contenido");
                int id_mensaje = Integer.parseInt(resultados.getString("id"));
                
                mensajes.add(new Mensaje(id_mensaje, emisor, remitente, contenido));
            }
            // accesoBD.close();
            return mensajes;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }

    }

    public Mensaje getMensaje(int id_mensaje) throws SQLException {

        Mensaje p;
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM mensaje WHERE id=" + id_mensaje;
            Statement st = accesoBD.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.first()) {

                Usuario emisor = rs.getEmisor("emisor");
                Usuario remitente = rs.getRemitente("remitente");
                String contenido= rs.getString("contenido");
                int edad = rs.getInt("edad");

                p = new Mensaje(id_mensaje, emisor, remitente, contenido);
                return p;

            } else {
                return null;

            }
        } catch (Exception e) {
            System.out.println("Error al Obtener");
            e.printStackTrace();
            return null;

        }
    }

    public boolean insertarMensaje(Mensaje p) {

        Connection accesoBD = con.getConexion();

        try {

            String sql = "INSERT INTO mensaje( emisor, remitente, contenido) VALUES ('" + p.getEmisor()) + "','" + p.getRemitente()) + "', '"+ p.getContenido() +"'   )";

            Statement st = accesoBD.createStatement();
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar mensaje");
            e.printStackTrace();
            return false;
        }
    }
        public boolean eliminar(int id) {
            Connection accesoBD = con.getConexion();
		
		boolean respuesta = false;
		try {
			String sql = "DELETE FROM mensaje WHERE id = '" + id + "'";
			PreparedStatement preparedStatement = con.getConexion().prepareStatement(sql);
			preparedStatement.execute();
			respuesta = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.cerrarConexion();
		}
		return respuesta;
	}
  


}

