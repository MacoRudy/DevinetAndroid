package com.example.devinet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.example.devinet.R;

public class ParametreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        menu.getItem(2).setVisible(false);
        getSupportActionBar().setTitle("PARAMETRES");
        return true;
    }
}