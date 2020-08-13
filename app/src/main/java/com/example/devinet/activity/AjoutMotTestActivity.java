package com.example.devinet.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.devinet.R;
import com.example.devinet.bo.Categorie;
import com.example.devinet.view_model.CategorieViewModel;
import com.facebook.stetho.Stetho;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

public class AjoutMotTestActivity extends BaseActivity {

    private static final String TAG = "Rudy";
    private static final int GALLERY_REQUEST = 9;
    private static final int CAMERA_PIC_REQUEST = 5;
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

    public void onClickBack(View view) {
        finish();
    }


    public void onClickCharger(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        Log.i(TAG, "onClickCharger: " + Uri.parse("/sdcard/Download/14.jpg"));
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 101);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();
            Log.i(TAG, "uri: " + uri);
            String path = getRealPathFromURI(this, uri);
            Log.i(TAG, "RealPathFromURI: " + path);
            String name = getFileName(uri);
            Log.i(TAG, "name: " + name);
            Log.i(TAG, "File saved in " + getFilesDir() + "/");

            try {
                insertInPrivateStorage(name, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertInPrivateStorage(String name, String path) throws IOException {

        FileOutputStream fos = openFileOutput(name, MODE_APPEND);

        File file = new File(path);

        byte[] bytes = getBytesFromFile(file);

        fos.write(bytes);
        fos.close();

        Toast.makeText(getApplicationContext(), "File saved in " + getFilesDir() + "/" + name, Toast.LENGTH_SHORT).show();
    }

    private byte[] getBytesFromFile(File file) throws IOException {
        byte[] data = FileUtils.readFileToByteArray(file);
        return data;
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public String getRealPathFromURI(Context cont, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = cont.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void onClickPrendre(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);


    }
}


//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            ImageView myImage = (ImageView) findViewById(R.id.iv_image);
//            myImage.setImageBitmap(photo);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            Log.i(TAG, "byteArray: " + byteArray);
//
//            Bitmap bitmap = BitmapFactory.decodeFile(byteArray.toString());
//            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
//            byte[] bitmapdata = blob.toByteArray();
//            bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
//            Log.i(TAG, "bitmap: " + bitmap);