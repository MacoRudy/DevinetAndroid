package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
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
    private FragmentActivity fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Stetho.initializeWithDefaults(this);
        fragment = this;

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        float pourcentage4Lettres = CalculProgression.getPourcentageNiveau(fragment,4);
        float pourcentage5Lettres = CalculProgression.getPourcentageNiveau(fragment,5);
        float pourcentage6Lettres = CalculProgression.getPourcentageNiveau(fragment,6);
        float pourcentageTotal = CalculProgression.pourcentageTotal(fragment);

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