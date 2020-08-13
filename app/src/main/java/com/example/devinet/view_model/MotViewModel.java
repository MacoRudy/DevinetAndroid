package com.example.devinet.view_model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.devinet.bo.Mot;
import com.example.devinet.repository.IMotRepository;
import com.example.devinet.repository.MotBDDRepository;
import java.util.List;

public class MotViewModel extends AndroidViewModel {

    private IMotRepository repo;

    public MotViewModel(@NonNull Application application) {
        super(application);
        repo = new MotBDDRepository(application);
    }

    public List<Mot> get() {
        return repo.get();
    }

    public void insert(Mot mot) {
        repo.insert(mot);
    }

    public void update(Mot mot) {
        repo.update(mot);
    }

    public void delete(Mot mot) {
        repo.delete(mot);
    }

    public void delete() {
        repo.delete();
    }

    public List<Mot> getListe(int nbreLettres, int idCat) {
        return repo.getListe(nbreLettres, idCat);
    }

    public List<Mot> getListeSelonLeNombre(int nbreLettres) {
        return repo.getListeSelonLeNombre(nbreLettres);
    }
}