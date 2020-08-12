package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.devinet.R;

public class ResultatActivity extends BaseActivity implements View.OnClickListener {
    private FragmentActivity fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        fragment = this;

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();

        float pourcentage4Lettres = CalculProgression.getPourcentageNiveau(fragment, 4);
        float pourcentage5Lettres = CalculProgression.getPourcentageNiveau(fragment, 5);
        float pourcentage6Lettres = CalculProgression.getPourcentageNiveau(fragment, 6);
        float pourcentageTotal = CalculProgression.pourcentageTotal(fragment);

        myEdit.putFloat("pourcentage4Lettres", pourcentage4Lettres);
        myEdit.putFloat("pourcentage5Lettres", pourcentage5Lettres);
        myEdit.putFloat("pourcentage6Lettres", pourcentage6Lettres);
        myEdit.putFloat("pourcentageTotal", pourcentageTotal);
        myEdit.apply();

        ((ProgressBar) findViewById(R.id.stats_progressbar)).setProgress(Math.round(pourcentageTotal));
        ((TextView) findViewById(R.id.tv_pourcentage)).setText(String.valueOf(Math.round(pourcentageTotal)) + "%");
        ((ProgressBar) findViewById(R.id.pb_1)).setProgress(Math.round(pourcentage4Lettres));
        ((ProgressBar) findViewById(R.id.pb_2)).setProgress(Math.round(pourcentage5Lettres));
        ((ProgressBar) findViewById(R.id.pb_3)).setProgress(Math.round(pourcentage6Lettres));

        findViewById(R.id.textView1).setOnClickListener(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.textView3).setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("RESULTAT - GENERAL");
        return true;
    }


    @Override
    public void onClick(View view) {
        int nbreLettres = 0;
        switch (view.getId()) {
            case R.id.textView1:
                nbreLettres = 4;
                break;
            case R.id.textView2:
                nbreLettres = 5;
                break;
            case R.id.textView3:
                nbreLettres = 6;
                break;
        }
        Intent intent = new Intent(this, DetailResultatActivity.class);
        intent.putExtra("nbreLettres", nbreLettres);
        startActivity(intent);
    }
}