package com.example.devinet.activity;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.devinet.R;
import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.CategorieViewModel;
import com.example.devinet.view_model.MotViewModel;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AjoutMotActivity extends BaseActivity {
    private static final String TAG = "Rudy";
    private static final int GALLERY_REQUEST = 9;
    private static final int REQUEST_IMAGE_CAPTURE = 5;
    private Context context = this;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_mot);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(true);
        getSupportActionBar().setTitle("PROPOSITION");
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Spinner spinner = findViewById(R.id.spin_categorie);
        CategorieViewModel viewModel = new ViewModelProvider(this).get(CategorieViewModel.class);
        List<Categorie> listeCategorie = viewModel.get();
        ArrayAdapter<Categorie> categorieAdapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_spinner_item, listeCategorie);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categorieAdapter);

    }

    public void onClickCharger(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
        Log.i(TAG, "onClickCharger: ");

    }

    public void onClickValider(View view) {
        MotViewModel viewModel = ViewModelProviders.of(this).get(MotViewModel.class);

        EditText etNom = findViewById(R.id.et_mot);
        String nom = etNom.getText().toString();

        if (nom.trim().length() <= 0) {
            etNom.setError("Votre mot");
            return;
        }
        Spinner spinner = findViewById(R.id.spin_categorie);
        Categorie categorie = (Categorie) spinner.getSelectedItem();
        Mot mot = new Mot(0, currentPhotoPath, nom, "", categorie.getIdCat());

        viewModel.insert(mot);

        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);
    }

    public void onClickBack(View view) {
        finish();
    }


    // 3 fonctions pour la prise de photo
    public void onClickPrendre(View view) {
        Log.i(TAG, "je suis dans le onClickPhoto");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // S'assure que la camera soit présente
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crée le path ou l'image va etre stockée
            Log.i(TAG, "je suis dans le if du onClick");
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i(TAG, "pb photo");
            }
            // Continue si le fichier a été créé
            if (photoFile != null) {
                Log.i(TAG, "le fichier a ete créé");
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }

    private File createImageFile() throws IOException {
        // crée le path de l'image
        Log.i(TAG, "je suis dans le createImageFile");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Path qu'aura la photo a mettre en BDD
        currentPhotoPath = image.getAbsolutePath();
        Log.i(TAG, currentPhotoPath);
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.i(TAG, "onActivityResult: " + requestCode + " " + resultCode + " " + data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {

            Log.i(TAG, "je suis au debut du onActivityResult");
            ImageView myImage = (ImageView) findViewById(R.id.iv_image);
            File imgFile = new File(currentPhotoPath);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            // affiche l'image sur la page

            myImage.setImageBitmap(myBitmap);
            onResume();
            Log.i(TAG, "je suis a la fin du onActivityResult Camera");
        }


        if (requestCode == GALLERY_REQUEST) {

            ImageView myImage = (ImageView) findViewById(R.id.iv_image);
            Uri imageChoisie = data.getData();

            // myImage.setImageURI(imageChoisie);
            currentPhotoPath = imageChoisie.toString();

            //readFile(currentPhotoPath);

            Log.i(TAG, "onActivityResult: " + currentPhotoPath);
            Picasso.get().load(currentPhotoPath).into(myImage);
//
//            Glide.with(context)
//                    .load(new File(uri.getPath()))
//                    .into(imageView);
            onResume();
            Log.i(TAG, "currentPhotoPath" + currentPhotoPath);
        }
    }

//    public void readFile(String filepath) {
//        try {
//            Uri uri = Uri.parse(filepath);
//            if (uri.getScheme() == null) {
//                uri = Uri.parse("file://" + filepath);
//            }
//            byte[] inputData = getBytes(context, uri);
//            Log.i(TAG, "inputData: " + inputData);
//            String base64Content = Base64.encodeToString(inputData, Base64.NO_WRAP);
//            Log.i(TAG, "base64Content: " + base64Content);
//
//           byte[] decode = Base64.decode(base64Content,Base64.NO_WRAP);
//
//            Log.i(TAG, "decode: "+ decode.toString());
//
//
//            String s = new String(decode, "UTF-8");
//            Uri uriDecode = Uri.parse(s);
//            Log.i(TAG, "uriDecode: " + uriDecode);
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
//    }
//
//    private static byte[] getBytes(InputStream inputStream) throws IOException {
//
//        byte[] bytesResult = null;
//        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//        int bufferSize = 1024;
//        byte[] buffer = new byte[bufferSize];
//        try {
//            int len;
//            while ((len = inputStream.read(buffer)) != -1) {
//                byteBuffer.write(buffer, 0, len);
//            }
//            bytesResult = byteBuffer.toByteArray();
//        } finally {
//            // close the stream
//            try {
//                byteBuffer.close();
//            } catch (IOException ignored) { /* do nothing */ }
//        }
//        return bytesResult;
//    }
//
//    private static byte[] getBytes(Context context, Uri uri) throws IOException {
//        InputStream iStream = context.getContentResolver().openInputStream(uri);
//        try {
//            return getBytes(iStream);
//        } finally {
//            // close the stream
//            try {
//                iStream.close();
//            } catch (IOException ignored) { /* do nothing */ }
//        }
//    }
//
}


