package com.example.devinet.repository;

import androidx.lifecycle.LiveData;

import com.example.devinet.bo.Mot;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO.
 */
public interface IMotRepository {

    void insert(Mot mot);

    List<Mot> get();

    Mot get(int id);

    void update(Mot mot);

    void delete(Mot mot);

    void delete();

   List<Mot> getListe(int nbreLettres, int idCat);

    List<Mot> getListeSelonLeNombre(int nbreLettres);

}
