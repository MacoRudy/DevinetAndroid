package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class JouerActivity extends BaseActivity {

    private static final String TAG = "rudy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        int pourcentage4Lettres = Math.round(sh.getFloat("pourcentage4Lettres", 0));
        int pourcentage5Lettres = Math.round(sh.getFloat("pourcentage5Lettres", 0));
        int pourcentage6Lettres = Math.round(sh.getFloat("pourcentage6Lettres", 0));


        ((ProgressBar) findViewById(R.id.pb_1)).setProgress(pourcentage4Lettres);
        ((ProgressBar) findViewById(R.id.pb_2)).setProgress(pourcentage5Lettres);
        ((ProgressBar) findViewById(R.id.pb_3)).setProgress(pourcentage6Lettres);
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

    public void onClickResetNiv1(View view) {
        Log.i(TAG, "onClickResetNiv1: ");
    }

    public void onClickResetNiv2(View view) {
        Log.i(TAG, "onClickResetNiv2: ");
    }

    public void onClickResetNiv3(View view) {
        Log.i(TAG, "onClickResetNiv3: ");
    }
}