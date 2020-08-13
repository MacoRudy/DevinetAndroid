package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.devinet.R;
import com.example.devinet.activity.adapter.ResultatAdapter;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.MotViewModel;
import java.util.List;

public class DetailResultatActivity extends BaseActivity {

    Context context = this;
    MotViewModel viewModel = null;
    ListView listeMots = null;
    int position = 0;
    int nbreLettres = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resultat);
        Intent intent = getIntent();
        nbreLettres = intent.getIntExtra("nbreLettres", 0);
        listeMots = findViewById(R.id.liste_mot);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel = ViewModelProviders.of(this).get(MotViewModel.class);
        List<Mot> listeSelonLeNombre = viewModel.getListeSelonLeNombre(nbreLettres);
        ResultatAdapter adapter = new ResultatAdapter(DetailResultatActivity.this, R.layout.style_ligne_mot, listeSelonLeNombre);
        listeMots.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);
        getSupportActionBar().setTitle("RESULTAT - DETAILS - NIVEAU " + (nbreLettres-3));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ab_accueil) {
            Intent intent = new Intent(this, ResultatActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}