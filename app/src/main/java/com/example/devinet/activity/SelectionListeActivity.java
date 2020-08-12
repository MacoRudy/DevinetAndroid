package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_liste);
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

        CategorieViewModel viewModel= ViewModelProviders.of(this).get(CategorieViewModel.class);
        listeCategorie = viewModel.get();

        tv1.setText(listeCategorie.get(0).getNom());
        tv2.setText(listeCategorie.get(1).getNom());
        tv3.setText(listeCategorie.get(2).getNom());
        tv4.setText(listeCategorie.get(3).getNom());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("NIVEAU " + niveau);
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
        Intent intent = new Intent(this,TrouverMotActivity.class);
        intent.putExtra("nbreLettres", niveau);
        intent.putExtra("categorie",idCat);
        intent.putExtra("nomCategorie",listeCategorie.get(idCat-1).getNom());
        startActivity(intent);
    }


}