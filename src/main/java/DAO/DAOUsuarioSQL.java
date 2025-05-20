package DAO;

import modelos.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOUsuarioSQL implements DAOUsuario{
    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        String sentencia = "INSERT INTO Usuario (nombre, clave) VALUES ('"
                + usuario.getNombre() + "','" + usuario.getClave() + "')";
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario, DAOManager dao) {
        String sentencia = "UPDATE Usuario set nombre = '"
                + usuario.getNombre() + "' clave = " + usuario.getClave() +
                "WHERE id = " + usuario.getId();
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Usuario usuario, DAOManager dao) {
        String sentencia = "DELETE from Usuario WHERE id = " + usuario.getId();
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> readAll(DAOManager dao) {
        ArrayList<Usuario> productos = new ArrayList<>();
        Usuario usuario;
        String sentencia = "SELECT * FROM Usuario;";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getInt("clave"),
                            rs.getString("nombre")
                            );
                    productos.add(usuario);
                }
            } catch (Exception e) {
                return productos;
            }
        } catch (Exception e) {
            return productos;
        }

        return productos;
    }

    @Override
    public Usuario readUsuario(DAOManager dao, int idUsuario) {
        Usuario usuario = null;
        String sentencia = "Select * from Usuario where id = " + idUsuario;
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    usuario = new Usuario(
                            idUsuario,
                            rs.getInt("clave"),
                            rs.getString("Nombre"));
                }
            }
            return usuario;
        } catch (SQLException e) {
            return usuario;
        }
    }
}
