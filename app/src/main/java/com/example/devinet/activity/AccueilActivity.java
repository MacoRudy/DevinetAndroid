package com.example.devinet.activity;

import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.devinet.R;
import com.facebook.stetho.Stetho;


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

        // Calculs des ratios pour l'activity JouerActivity
        float pourcentage4Lettres = CalculProgression.getPourcentageNiveau(fragment, 4);
        float pourcentage5Lettres = CalculProgression.getPourcentageNiveau(fragment, 5);
        float pourcentage6Lettres = CalculProgression.getPourcentageNiveau(fragment, 6);

        // Calcul du ratio total pour l'activity ResultatActivity
        float pourcentageTotal = CalculProgression.pourcentageTotal(fragment);

        // Stockés en SharedPreferences
        // JouerActivity
        myEdit.putFloat("pourcentage4Lettres", pourcentage4Lettres);
        myEdit.putFloat("pourcentage5Lettres", pourcentage5Lettres);
        myEdit.putFloat("pourcentage6Lettres", pourcentage6Lettres);

        // ResultatActivity
        myEdit.putFloat("pourcentageTotal", pourcentageTotal);
        myEdit.apply();

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