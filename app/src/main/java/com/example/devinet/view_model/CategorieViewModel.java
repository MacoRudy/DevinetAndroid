package com.example.devinet.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.devinet.bo.Categorie;
import com.example.devinet.repository.CategorieBDDRepository;
import com.example.devinet.repository.ICategorieRepository;
import java.util.List;

public class CategorieViewModel extends AndroidViewModel {

    private ICategorieRepository repo;
    private List<Categorie> observateur = null;

    public CategorieViewModel(@NonNull Application application) {
        super(application);
        repo = new CategorieBDDRepository(application);
        observateur = repo.get();
    }

    public List<Categorie> get() {
        return observateur;
    }

    public List<String> getNom() {
        return repo.getNom();
    }

    public void insert(Categorie categorie) {
        repo.insert(categorie);
    }

    public void update(Categorie categorie) {
        repo.update(categorie);
    }

    public void delete(Categorie categorie) {
        repo.delete(categorie);
    }

    public void delete() {
        repo.delete();
    }
}
