package DAO;

import modelos.Fichaje;
import modelos.Usuario;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOFichajeSQL implements DAOFichaje{

    @Override
    public boolean insert(Fichaje fichaje, DAOManager dao) {
        String sentencia = "INSERT INTO Fichaje (id_usuario, tipo, fecha, hora) VALUES ("
                + fichaje.getUsuario().getId() + ",'"
                + fichaje.getTipo() + "',"
                + Utils.extraeFecha(fichaje.getFechaHoraFichaje()) + ","
                + Utils.extraeHora(fichaje.getFechaHoraFichaje()) + ");";
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Fichaje fichaje, DAOManager dao) {
        String sentencia = "UPDATE Fichaje set" +
                " id_trabajador = " + fichaje.getUsuario().getId() +
                " tipo = '" + fichaje.getTipo() + "'" +
                " fecha = " + Utils.extraeFecha(fichaje.getFechaHoraFichaje())  +
                " hora = " + Utils.extraeHora(fichaje.getFechaHoraFichaje())  +
                "WHERE id = " + fichaje.getId();
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Fichaje fichaje, DAOManager dao) {
        String sentencia = "DELETE from Usuario WHERE id = " + fichaje.getId();
        try {
            Statement stmt = dao.getConn().createStatement();
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public ArrayList<Fichaje> readAll(DAOManager dao) {
        ArrayList<Fichaje> fichajes = new ArrayList<>();
        Fichaje fichaje;
        String sentencia = "Select * from Fichaje;";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    DAOUsuarioSQL daoUsuarioSQL = new DAOUsuarioSQL();
                    Usuario usuario = daoUsuarioSQL.readUsuario(dao,rs.getInt("id_usuario"));
                    LocalDateTime horaFichaje = Utils.formateaFechaHora(rs.getDate("fecha"),rs.getTime("hora"));
                    fichaje = new Fichaje(
                            rs.getInt("id"),
                            usuario,
                            rs.getString("tipo"),
                            horaFichaje
                            );
                    fichajes.add(fichaje);
                }

            } catch (Exception e) {
                return fichajes;
            }
        } catch (SQLException e) {
            return fichajes;
        }
        return fichajes;
    }
}
