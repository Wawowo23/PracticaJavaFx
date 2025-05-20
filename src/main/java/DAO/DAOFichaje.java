package DAO;

import modelos.Fichaje;
import modelos.Usuario;

import java.util.ArrayList;

public interface DAOFichaje {

    public boolean insert(Fichaje fichaje, DAOManager dao);
    public boolean update(Fichaje fichaje, DAOManager dao);
    public boolean delete(Fichaje fichaje, DAOManager dao);
    public ArrayList<Fichaje> readAll(DAOManager dao);
}
