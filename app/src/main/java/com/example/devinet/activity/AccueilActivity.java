package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.devinet.R;
import com.example.devinet.bo.Mot;
import com.example.devinet.dal.CategorieDAO;
import com.example.devinet.dal.MotDAO;
import com.example.devinet.repository.IMotRepository;
import com.example.devinet.repository.MotBDDRepository;
import com.example.devinet.view_model.MotViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class AccueilActivity<MyApplication> extends BaseActivity {
    private static final String TAG = "rudy";
    private float bonnesReponses = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Stetho.initializeWithDefaults(this);
        MotViewModel viewModel = ViewModelProviders.of(this).get(MotViewModel.class);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // liste de tous les mots
        List<Mot> listeMot = viewModel.get();
        for (Mot mot : listeMot) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentageTotal = bonnesReponses / listeMot.size() * 100;
        bonnesReponses = 0;

        // liste des mots de 4 lettres

        List<Mot> listeMotDe4Lettres = viewModel.getListeSelonLeNombre(4);
        for (Mot mot : listeMotDe4Lettres) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentage4Lettres = bonnesReponses / listeMotDe4Lettres.size() * 100;
        bonnesReponses = 0;

        // liste des mots de 5lettres

        List<Mot> listeMotDe5Lettres = viewModel.getListeSelonLeNombre(5);
        for (Mot mot : listeMotDe5Lettres) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentage5Lettres = bonnesReponses / listeMotDe5Lettres.size() * 100;
        bonnesReponses = 0;

        // liste des mots de 6 lettres

        List<Mot> listeMotDe6Lettres = viewModel.getListeSelonLeNombre(6);
        for (Mot mot : listeMotDe6Lettres) {
            if (mot.getProposition().equals(mot.getMot().toUpperCase())) {
                bonnesReponses++;
            }
        }
        float pourcentage6Lettres = bonnesReponses / listeMotDe6Lettres.size() * 100;
        bonnesReponses = 0;

        myEdit.putFloat("pourcentage4Lettres", pourcentage4Lettres);
        myEdit.putFloat("pourcentage5Lettres", pourcentage5Lettres);
        myEdit.putFloat("pourcentage6Lettres", pourcentage6Lettres);
        myEdit.putFloat("pourcentageTotal", pourcentageTotal);
        myEdit.apply();


//        IMotRepository motDao = new MotBDDRepository(this);
//        Mot chat = motDao.get(3);
//        motDao.delete(chat);

    }

    public void onClickQuit(View view) {
        Intent intent = new Intent(this, QuitterActivity.class);
        startActivity(intent);
    }

    public void onClickAjouter(View view) {
        Intent intent = new Intent(this, AjoutMotActivity.class);
        startActivity(intent);
    }

    public void onClickJouer(View view) {
        Intent intent = new Intent(this, JouerActivity.class);
        startActivity(intent);
    }

    public void onClickResultat(View view) {
        Intent intent = new Intent(this, ResultatActivity.class);
        startActivity(intent);
    }
}