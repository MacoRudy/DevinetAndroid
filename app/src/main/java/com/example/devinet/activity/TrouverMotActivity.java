package com.example.devinet.activity;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.devinet.R;
import com.example.devinet.bo.Mot;
import com.example.devinet.repository.IMotRepository;
import com.example.devinet.repository.MotBDDRepository;
import com.example.devinet.view_model.MotViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrouverMotActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "rudy";
    private int nbreLettres = 0;
    private int idCategorie = 0;
    private MotViewModel viewModel = null;
    private List<Mot> listeMot = null;
    private int numero = 0;
    private String categorie = "";
    private int essai = 0;
    private ArrayList<Button> buttons = null;
    private ArrayList<TextView> textViews = null;
    private ImageButton valider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouver_mot);
        Intent intent = getIntent();
        nbreLettres = intent.getIntExtra("nbreLettres", 0);
        idCategorie = intent.getIntExtra("categorie", 0);
        categorie = intent.getStringExtra("nomCategorie");
        Log.i(TAG, "nbreLettre " + nbreLettres + " idCat " + idCategorie);
        viewModel = ViewModelProviders.of(this).get(MotViewModel.class);
        listeMot = viewModel.getListe(nbreLettres, idCategorie);
        valider = findViewById(R.id.ib_valide);
        valider.setEnabled(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("LISTE  " + categorie + " - MOT N°" + (numero + 1));
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv5 = findViewById(R.id.tv_5);
        TextView tv6 = findViewById(R.id.tv_6);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Log.i(TAG, "tv1 " + tv5.getVisibility());
        switch (nbreLettres) {
            case 5:
                tv6.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                break;
            case 4:
                tv5.setVisibility(View.INVISIBLE);
                tv6.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                break;

        }
           ImageView image = findViewById(R.id.iv_photo);
       Uri uri = Uri.parse(listeMot.get(numero).getImg());
//        Glide.with(this).load(new File(uri.getPath())).into(image);
      //  Picasso.get().load(uri).into(image);
        Picasso.get().load(listeMot.get(numero).getImg()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: bah c'est bien");
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: "+e.toString());
            }
        });
        afficherMot(listeMot.get(numero).getMot());
        Log.i(TAG, "onResume: " + listeMot.get(numero).getImg());
        Log.i(TAG, "onResume: " + uri);
    }


    public void afficherMot(String mot) {
        getSupportActionBar().setTitle("LISTE  " + categorie + " - MOT N°" + (numero + 1));

        char[] motMelange = melange(mot);
        Log.i(TAG, "afficherMot: " + motMelange[0]);




//        ImageView image = findViewById(R.id.iv_photo);
//        //Bitmap myBitmap = BitmapFactory.decodeFile(mot.getImg());
//        image.setImageURI(Uri.parse(mot.getImg()));
//        File imgFile = new File(mot.getImg());
//        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//        // affiche l'image sur la page
//        getContentResolver().openFile(Uri.parse(mot.getImg()),)
//        image.setImageBitmap(myBitmap);


        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.btn_1));
        buttons.add((Button) findViewById(R.id.btn_2));
        buttons.add((Button) findViewById(R.id.btn_3));
        buttons.add((Button) findViewById(R.id.btn_4));
        buttons.add((Button) findViewById(R.id.btn_5));
        buttons.add((Button) findViewById(R.id.btn_6));

        Log.i(TAG, "afficherMot: ");


        for (int i = 0; i < motMelange.length; i++) {
            buttons.get(i).setText(String.valueOf(motMelange[i]));
            buttons.get(i).setOnClickListener(this);
        }

//
//        for (int i = 1; i < motMelange.length + 1; i++) {
//            ((Button) findViewById(this.getResources().getIdentifier("btn_" + i, "id", this.getPackageName()))).setText(String.valueOf(motMelange[i]));
//        }
    }

    public static char[] melange(String mot) {
        Random random = new Random();
        char[] motMelange = mot.toCharArray();

        for (int i = 0; i < motMelange.length; i++) {
            int j = random.nextInt(motMelange.length);
            char temp = motMelange[i];
            motMelange[i] = motMelange[j];
            motMelange[j] = temp;
        }

        return motMelange;
    }


    @Override
    public void onClick(View view) {
        String texte = ((Button) view).getText().toString();
        Log.i(TAG, "onClick: " + texte);

        textViews = new ArrayList<>();

        if (nbreLettres > 4) {
            textViews.add((TextView) findViewById(R.id.tv_5));
        }
        textViews.add((TextView) findViewById(R.id.tv_1));
        textViews.add((TextView) findViewById(R.id.tv_2));
        textViews.add((TextView) findViewById(R.id.tv_3));
        textViews.add((TextView) findViewById(R.id.tv_4));
        if (nbreLettres > 5) {
            textViews.add((TextView) findViewById(R.id.tv_6));
        }
        textViews.get(essai).setText(texte.toUpperCase());
        essai++;
        if (essai >= textViews.size()) {
            valider.setEnabled(true);
        }
        ((Button) view).setText("");
        ((Button) view).setEnabled(false);
    }

    public void onClickEffacer(View view) {
        afficherMot(listeMot.get(numero).getMot());
        essai = 0;
        valider.setEnabled(false);
        for (Button b : buttons) {
            b.setEnabled(true);
        }
        for (TextView t : textViews) {
            t.setText("");
        }
    }

    public void onClickValider(View view) {

        StringBuilder proposition = new StringBuilder();

        for (TextView t : textViews) {
            proposition.append(t.getText());
        }

        listeMot.get(numero).setProposition(proposition.toString());

        MotViewModel viewModel = ViewModelProviders.of(this).get(MotViewModel.class);
             viewModel.update(listeMot.get(numero));


        numero++;
        valider.setEnabled(false);
        if (numero >= listeMot.size()) {
            Intent intent = new Intent(this, ResultatActivity.class);
            startActivity(intent);
        } else {
            afficherMot(listeMot.get(numero).getMot());
            essai = 0;
            for (Button b : buttons) {
                b.setEnabled(true);
            }
            for (TextView t : textViews) {
                t.setText("");
            }
        }

    }

    public void onClickNext(View view) {
        numero++;
        valider.setEnabled(false);
        if (numero >= listeMot.size()) {
            Intent intent = new Intent(this, ResultatActivity.class);
            startActivity(intent);
        } else {
            afficherMot(listeMot.get(numero).getMot());
            essai = 0;
            for (Button b : buttons) {
                b.setEnabled(true);
            }
            if (textViews != null) {
                for (TextView t : textViews) {
                    t.setText("");
                }
            }
        }
    }

}
