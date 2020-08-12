package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.devinet.R;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class JouerActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "rudy";
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);
        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        int pourcentage4Lettres = Math.round(sh.getFloat("pourcentage4Lettres", 0));
        int pourcentage5Lettres = Math.round(sh.getFloat("pourcentage5Lettres", 0));
        int pourcentage6Lettres = Math.round(sh.getFloat("pourcentage6Lettres", 0));


        ((ProgressBar) findViewById(R.id.pb_1)).setProgress(pourcentage4Lettres);
        ((ProgressBar) findViewById(R.id.pb_2)).setProgress(pourcentage5Lettres);
        ((ProgressBar) findViewById(R.id.pb_3)).setProgress(pourcentage6Lettres);

        findViewById(R.id.btnNiv1).setOnClickListener(this);
        findViewById(R.id.btnNiv2).setOnClickListener(this);
        findViewById(R.id.btnNiv3).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("SELECTION NIVEAU");
        return true;
    }

    public void onClickNiveau1(View view) {
        Log.i(TAG, "onClickNiveau1: ");
        Intent intent = new Intent(this, SelectionListeActivity.class);
        intent.putExtra("niveau", 4);
        startActivity(intent);
    }

    public void onClickNiveau2(View view) {
        Log.i(TAG, "onClickNiveau2: ");
        Intent intent = new Intent(this, SelectionListeActivity.class);
        intent.putExtra("niveau", 5);
        startActivity(intent);
    }

    public void onClickNiveau3(View view) {
        Log.i(TAG, "onClickNiveau3: ");
        Intent intent = new Intent(this, SelectionListeActivity.class);
        intent.putExtra("niveau", 6);
        startActivity(intent);
    }

   @Override
    public void onClick(View view) {

        int nbreLettres = 0;
        String pourcentage = "";
        switch (view.getId()) {
            case R.id.btnNiv1:
                nbreLettres = 4;
                pourcentage = "pourcentage4Lettres";
                break;
            case R.id.btnNiv2:
                nbreLettres = 5;
                pourcentage = "pourcentage5Lettres";
                break;
            case R.id.btnNiv3:
                nbreLettres = 6;
                pourcentage = "pourcentage6Lettres";
                break;
        }
       Log.i(TAG, "onClick: " + nbreLettres);
        MotViewModel viewModel = ViewModelProviders.of(this).get(MotViewModel.class);
        List<Mot> listeMot = viewModel.getListeSelonLeNombre(nbreLettres);

        for (Mot mot : listeMot) {
            mot.setProposition("");
            viewModel.update(mot);
        }
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putFloat(pourcentage, 0);
        myEdit.apply();
        finish();
        startActivity(getIntent());
    }
}