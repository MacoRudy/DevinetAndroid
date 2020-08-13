package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.devinet.R;
import com.example.devinet.bo.Categorie;
import com.example.devinet.view_model.CategorieViewModel;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class SelectionListeActivity extends BaseActivity implements View.OnClickListener {

    int niveau = 1;
    int idCat = 0;
    List<Categorie> listeCategorie = null;
    private FragmentActivity fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_liste);
        fragment = this;

        Intent intent = getIntent();
        niveau = intent.getIntExtra("niveau", 0);
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

        CategorieViewModel viewModel = ViewModelProviders.of(this).get(CategorieViewModel.class);
        listeCategorie = viewModel.get();

        tv1.setText(listeCategorie.get(0).getNom());
        tv2.setText(listeCategorie.get(1).getNom());
        tv3.setText(listeCategorie.get(2).getNom());
        tv4.setText(listeCategorie.get(3).getNom());

        float pourcentageCat1 = CalculProgression.getPourcentageNiveauEtCategorie(fragment, niveau, 1);
        float pourcentageCat2 = CalculProgression.getPourcentageNiveauEtCategorie(fragment, niveau, 2);
        float pourcentageCat3 = CalculProgression.getPourcentageNiveauEtCategorie(fragment, niveau, 3);
        float pourcentageCat4 = CalculProgression.getPourcentageNiveauEtCategorie(fragment, niveau, 4);

        ((ProgressBar) findViewById(R.id.pb_1)).setProgress(Math.round(pourcentageCat1));
        ((ProgressBar) findViewById(R.id.pb_2)).setProgress(Math.round(pourcentageCat2));
        ((ProgressBar) findViewById(R.id.pb_3)).setProgress(Math.round(pourcentageCat3));
        ((ProgressBar) findViewById(R.id.pb_4)).setProgress(Math.round(pourcentageCat4));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("NIVEAU " + (niveau - 3));
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                idCat = 1;
                break;
            case R.id.textView2:
                idCat = 2;
                break;
            case R.id.textView3:
                idCat = 3;
                break;
            case R.id.textView4:
                idCat = 4;
                break;
        }
        Intent intent = new Intent(this, TrouverMotActivity.class);
        intent.putExtra("nbreLettres", niveau);
        intent.putExtra("categorie", idCat);
        intent.putExtra("nomCategorie", listeCategorie.get(idCat - 1).getNom());
        startActivity(intent);
    }


}