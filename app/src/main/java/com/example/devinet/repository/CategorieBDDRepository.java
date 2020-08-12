package com.example.devinet.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.devinet.bo.Categorie;
import com.example.devinet.dal.AppDatabase;
import com.example.devinet.dal.CategorieDAO;

import java.util.List;

public class CategorieBDDRepository implements ICategorieRepository {

    private CategorieDAO CategorieDAO;

    private CategorieDAO categorieDAO;

    public CategorieBDDRepository(Context context) {
        AppDatabase maBDD = AppDatabase.getInstance(context);
        categorieDAO = maBDD.getCategorieDAO();
    }


    @Override
    public void insert(final Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>() {
            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDAO.insert(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public List<Categorie> get() {
        return categorieDAO.get();
    }

    @Override
    public List<String> getNom() {
        return categorieDAO.getNom();
    }

    @Override
    public Categorie get(int id) {
        return categorieDAO.get(id);
    }

    @Override
    public void update(Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>() {
            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDAO.update(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public void delete(Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>() {
            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDAO.delete(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public void delete() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                categorieDAO.delete();
                return null;
            }
        }.execute();
    }
}
