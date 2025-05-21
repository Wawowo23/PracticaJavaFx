package controlador;

import DAO.DAOFichajeSQL;
import DAO.DAOManager;
import DAO.DAOUsuarioSQL;
import modelos.Fichaje;
import modelos.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Controlador {

    //Atributos
    private DAOManager dao;

    // Constructor

    public Controlador() {
        // Establecemos la conexión con la base de datos
        dao = DAOManager.getSinglentonInstance();
    }

    // Metodos

    // Metodo que trae desde la base de datos a todos los usuarios y los mete en una ArrayList
    public ArrayList<Usuario> getUsuarios () {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        DAOUsuarioSQL daoUsuarioSQL = new DAOUsuarioSQL();
        try {
            dao.open();
            usuarios = daoUsuarioSQL.readAll(dao);
            dao.close();
            return usuarios;
        } catch (Exception e) {
            return usuarios;
        }
    }

    // Metodo que trae desde la base de datos a todos los fichajes de los usuarios y los mete en una ArrayList
    public ArrayList<Fichaje> getFichajes () {
        ArrayList<Fichaje> fichajes = new ArrayList<>();
        DAOFichajeSQL daoFichajeSQL = new DAOFichajeSQL();
        try {
            dao.open();
            fichajes = daoFichajeSQL.readAll(dao);
            dao.close();

        } catch (Exception e) {
            return fichajes;
        }
        Collections.sort(fichajes);
        return fichajes;
    }

    // Metodo que busca entre todos los usuarios al que corresponda con la clave introducida
    public Usuario login(int clave) {
        ArrayList<Usuario> usuarios = getUsuarios();
        for (Usuario u : usuarios)
            if (u.login(clave)) return u;
        return null;
    }

    // Metodo que registra un fichaje de un usuario pasado por parámetros
    public boolean registraFichaje (Usuario usuario, String tipoFichaje) {
        int nuevaId = generaIdFichaje();
        Fichaje fichajeInsertado = new Fichaje(nuevaId,usuario,tipoFichaje, LocalDateTime.now());
        DAOFichajeSQL daoFichajeSQL = new DAOFichajeSQL();
        boolean resultado = false;
        try {
            dao.open();
            resultado = daoFichajeSQL.insert(fichajeInsertado,dao);
            dao.close();
            return resultado;
        } catch (Exception e) {
            return resultado;
        }
    }

    // Metodo que se fija en el último fichaje de un usuario pasado por parametros y decide si su nuevo fichaje
    // será de entrada o salida
    public String generaTipoNuevoFichaje(Usuario usuario) {
        ArrayList<Fichaje> fichajes = getFichajes();

        for (Fichaje f : fichajes) {
            if (f.getUsuario().getId() == usuario.getId())
                if (f.getTipo().equals("Entrada")) return "Salida";
                else return "Entrada";
        }
        return "Entrada";
    }

    private int generaIdFichaje() {
        return getFichajes().size();
    }

    // Metodo que comprueba si un usuario es un admin
    public boolean compruebaAdmin(Usuario usuario) {
        return usuario.getNombre().equalsIgnoreCase("Admin") && usuario.getClave() == 1234;
    }
}
