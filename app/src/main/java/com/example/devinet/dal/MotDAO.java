package com.example.devinet.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.devinet.bo.Mot;
import java.util.List;

@Dao
public interface MotDAO {

    @Insert
    void insert(Mot mot);

    @Query("SELECT * FROM Mot")
    List<Mot> get();

    @Query("SELECT * FROM Mot WHERE idMot=:id")
    Mot get(int id);

    @Update
    void update(Mot mot);

    @Delete
    void delete(Mot mot);

    @Query("DELETE FROM Mot")
    void delete();

    @Query("SELECT * FROM Mot WHERE LENGTH(mot)= :nbreLettres AND idCat=:idCat")
   List<Mot> getListe(int nbreLettres,int idCat);

    @Query("SELECT * FROM Mot WHERE LENGTH(mot)= :nbreLettres")
    List<Mot> getListeSelonLeNombre(int nbreLettres);
}
