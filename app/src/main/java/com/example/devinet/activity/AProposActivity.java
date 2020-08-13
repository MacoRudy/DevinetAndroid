package com.example.devinet.activity;

import android.os.Bundle;
import android.view.Menu;
import com.example.devinet.R;

public class AProposActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(false);
        getSupportActionBar().setTitle("A PROPOS");
        return true;
    }
}