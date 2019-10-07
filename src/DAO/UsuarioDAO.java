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
import modelo.Usuario;

public class UsuarioDAO {

    Conexion con;

    public UsuarioDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario ";

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_usuario = Integer.parseInt(resultados.getString("id"));
                String nombre = resultados.getString("nombre");
                
                usuarios.add(new Usuario(id_usuario,nombre ));
            }
            // accesoBD.close();
            return usuarios;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }

    }

    public Usuario getUsuario(int id_usuario) throws SQLException {

        Usuario p;
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario WHERE id=" + id_usuario;
            Statement st = accesoBD.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.first()) {

                String nombre = rs.getString("nombre");
                
                p = new Usuario(id_usuario,nombre);
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

    public boolean insertar(Usuario p) {

        Connection accesoBD = con.getConexion();

        try {

            //INSSERT INTO persona(nombre, apellido, edad) VALUES('Pedro', 'Fuentes', 18);
            String sql = "INSERT INTO usuario(nombre) VALUES ('" + p.getNombre() + ")";

            Statement st = accesoBD.createStatement();
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario");
            e.printStackTrace();
            return false;
        }
    }
        public boolean eliminar(int id_usuario) {
            Connection accesoBD = con.getConexion();
		
		boolean respuesta = false;
		try {
			String sql = "DELETE FROM usuario WHERE id = '" + id_usuario + "'";
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
