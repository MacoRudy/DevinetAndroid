package com.example.devinet.repository;

import com.example.devinet.bo.Categorie;
import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO.
 */
public interface ICategorieRepository {

    void insert(Categorie categorie);

    List<Categorie> get();

    List<String> getNom();

    Categorie get(int id);

    void update(Categorie categorie);

    void delete(Categorie categorie);

    void delete();
}

