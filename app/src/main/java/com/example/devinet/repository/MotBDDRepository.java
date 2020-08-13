package com.example.devinet.repository;

import android.content.Context;
import android.os.AsyncTask;
import com.example.devinet.bo.Mot;
import com.example.devinet.dal.AppDatabase;
import com.example.devinet.dal.MotDAO;
import java.util.List;

public class MotBDDRepository implements IMotRepository {

    private MotDAO motDAO;

    public MotBDDRepository(Context context) {
        AppDatabase maBDD = AppDatabase.getInstance(context);
        motDAO = maBDD.getMotDAO();
    }

    @Override
    public void insert(final Mot mot) {
        new AsyncTask<Mot, Void, Void>() {
            @Override
            protected Void doInBackground(Mot... mots) {
                motDAO.insert(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public List<Mot> get() {
        return motDAO.get();
    }

    @Override
    public Mot get(int id) {
        return motDAO.get(id);
    }

    @Override
    public void update(Mot mot) {
        new AsyncTask<Mot, Void, Void>() {
            @Override
            protected Void doInBackground(Mot... mots) {
                motDAO.update(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void delete(Mot mot) {
        new AsyncTask<Mot, Void, Void>() {
            @Override
            protected Void doInBackground(Mot... mots) {
                motDAO.delete(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void delete() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                motDAO.delete();
                return null;
            }
        }.execute();
    }

    @Override
    public List<Mot> getListe(int nbreLettres,int idCat) {
        return motDAO.getListe(nbreLettres,idCat);
    }

    @Override
    public List<Mot> getListeSelonLeNombre(int nbreLettres) {
        return motDAO.getListeSelonLeNombre(nbreLettres);
    }
}
