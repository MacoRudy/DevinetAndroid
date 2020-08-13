package com.example.devinet.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class CalculProgression extends Application {

    private static float bonnesReponses;
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }


    public static float pourcentageTotal(FragmentActivity activity) {

        MotViewModel viewModel = ViewModelProviders.of(activity).get(MotViewModel.class);
        List<Mot> listeMot = viewModel.get();
        for (Mot mot : listeMot) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentageTotal = bonnesReponses / listeMot.size() * 100;
        bonnesReponses = 0;

        return pourcentageTotal;
    }

    public static float getPourcentageNiveau(FragmentActivity activity, int nbreLettre) {

        MotViewModel viewModel = ViewModelProviders.of(activity).get(MotViewModel.class);
        List<Mot> listeMot = viewModel.getListeSelonLeNombre(nbreLettre);

        for (Mot mot : listeMot) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentageNiveau = bonnesReponses / listeMot.size() * 100;
        bonnesReponses = 0;
        return pourcentageNiveau;
    }

    public static float getPourcentageNiveauEtCategorie(FragmentActivity activity, int nbreLettre, int idCat) {

        MotViewModel viewModel = ViewModelProviders.of(activity).get(MotViewModel.class);
        List<Mot> listeMot = viewModel.getListe(nbreLettre,idCat);

        for (Mot mot : listeMot) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentageNiveauetCategorie = bonnesReponses / listeMot.size() * 100;
        bonnesReponses = 0;
        return pourcentageNiveauetCategorie;
    }
}
