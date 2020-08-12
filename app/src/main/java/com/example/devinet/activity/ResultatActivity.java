package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.devinet.R;

public class ResultatActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int pourcentageTotal = Math.round(sh.getFloat("pourcentageTotal", 0));
        int pourcentage4Lettres = Math.round(sh.getFloat("pourcentage4Lettres", 0));
        int pourcentage5Lettres = Math.round(sh.getFloat("pourcentage5Lettres", 0));
        int pourcentage6Lettres = Math.round(sh.getFloat("pourcentage6Lettres", 0));


        ((ProgressBar) findViewById(R.id.stats_progressbar)).setProgress(pourcentageTotal);
        ((TextView) findViewById(R.id.tv_pourcentage)).setText(String.valueOf(pourcentageTotal) + "%");
        ((ProgressBar) findViewById(R.id.pb_1)).setProgress(pourcentage4Lettres);
        ((ProgressBar) findViewById(R.id.pb_2)).setProgress(pourcentage5Lettres);
        ((ProgressBar) findViewById(R.id.pb_3)).setProgress(pourcentage6Lettres);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("RESULTAT - GENERAL");
        return true;
    }

    public void onClickNiveau1(View view) {
    }


    public void onClickNiveau2(View view) {
    }

    public void onClickNiveau3(View view) {
    }

    public void onClickDetails(View view) {
    }
}