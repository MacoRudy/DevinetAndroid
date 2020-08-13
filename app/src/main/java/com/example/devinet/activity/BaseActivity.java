package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.devinet.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_param:
                Intent param = new Intent(this, ParametreActivity.class);
                startActivity(param);
                return true;

            case R.id.ab_propos:
                Intent aPropos = new Intent(this, AProposActivity.class);
                startActivity(aPropos);
                return true;

            case R.id.ab_accueil:
                Intent accueil = new Intent(this, AccueilActivity.class);
                startActivity(accueil);
                return true;

            case R.id.ab_accueil2:
                Intent accueil2 = new Intent(this, AccueilActivity.class);
                startActivity(accueil2);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}