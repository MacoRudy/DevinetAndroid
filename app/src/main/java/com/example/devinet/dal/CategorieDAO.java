package com.example.devinet.dal;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.devinet.bo.Categorie;


import java.util.List;

@Dao
public interface CategorieDAO {

    @Insert
    void insert(Categorie categorie);

    @Query("SELECT * FROM Categorie")
    List<Categorie> get();


    @Query("SELECT nom FROM Categorie")
    List<String> getNom();


    @Query("SELECT * FROM Categorie WHERE idCat=:id")
    Categorie get(int id);

    @Update
    void update(Categorie categorie);

    @Delete
    void delete(Categorie categorie);

    @Query("DELETE FROM Categorie")
    void delete();
}

