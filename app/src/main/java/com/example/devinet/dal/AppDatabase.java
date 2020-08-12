package com.example.devinet.dal;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;

@Database(entities = {Mot.class, Categorie.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Permet de fournir une instance de la DAO aux couches superieures
     */

    public abstract MotDAO getMotDAO();

    public abstract CategorieDAO getCategorieDAO();


    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "devinet.db")
                    // creation de la bdd
                    .addCallback(roomFixture)
                    // pour ne pas a avoir de faire Async pour acceder a la bdd
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }

    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new AsyncTask<AppDatabase, Void, Void>() {

                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    CategorieDAO catDao = INSTANCE.getCategorieDAO();
                    Categorie fruit = new Categorie(0, "Fruit");
                    Categorie legume = new Categorie(0, "Legume");
                    Categorie animal = new Categorie(0, "Animal");
                    Categorie divers = new Categorie(0, "Divers");

                    catDao.insert(fruit);
                    catDao.insert(legume);
                    catDao.insert(animal);
                    catDao.insert(divers);
                    return null;
                }


            }.execute(INSTANCE);
        }
    };
}











