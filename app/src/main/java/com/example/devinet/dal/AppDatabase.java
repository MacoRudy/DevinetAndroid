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

                    MotDAO motDao = INSTANCE.getMotDAO();
                    Mot ballon = new Mot(0, "ballon", "ballon", "", 4);
                    Mot tong = new Mot(0, "tong", "tong", "", 4);
                    Mot four = new Mot(0, "four", "four", "", 4);
                    Mot pain = new Mot(0, "pain", "pain", "", 4);
                    Mot gomme = new Mot(0, "gomme", "gomme", "", 4);
                    Mot stylo = new Mot(0, "stylo", "stylo", "", 4);
                    Mot slip = new Mot(0, "slip", "slip", "", 4);
                    Mot maison = new Mot(0, "maison", "maison", "", 4);
                    Mot verre = new Mot(0, "verre", "verre", "", 4);
                    Mot table = new Mot(0, "table", "table", "", 4);
                    Mot pied = new Mot(0, "pied", "pied", "", 4);
                    Mot main = new Mot(0, "main", "main", "", 4);

                    Mot poisson = new Mot(0, "poisson", "poisson", "", 3);
                    Mot zebre = new Mot(0, "zebre", "zebre", "", 3);
                    Mot ours = new Mot(0, "ours", "ours", "", 3);
                    Mot lion = new Mot(0, "lion", "lion", "", 3);
                    Mot porc = new Mot(0, "porc", "porc", "", 3);
                    Mot chevre = new Mot(0, "chevre", "chevre", "", 3);
                    Mot mouton = new Mot(0, "mouton", "mouton", "", 3);
                    Mot vache = new Mot(0, "vache", "vache", "", 3);
                    Mot cheval = new Mot(0, "cheval", "cheval", "", 3);
                    Mot lapin = new Mot(0, "lapin", "lapin", "", 3);
                    Mot souris = new Mot(0, "souris", "souris", "", 3);
                    Mot chat = new Mot(0, "chat", "chat", "", 3);
                    Mot chien = new Mot(0, "chien", "chien", "", 3);
                    Mot poule = new Mot(0, "poule", "poule", "", 3);
                    Mot loup = new Mot(0, "loup", "loup", "", 3);

                    Mot courge = new Mot(0, "courge", "courge", "", 2);
                    Mot celeri = new Mot(0, "celeri", "celeri", "", 2);
                    Mot mais = new Mot(0, "mais", "mais", "", 2);
                    Mot radis = new Mot(0, "radis", "radis", "", 2);
                    Mot endive = new Mot(0, "endive", "endive", "", 2);
                    Mot navet = new Mot(0, "navet", "navet", "", 2);
                    Mot oignon = new Mot(0, "oignon", "oignon", "", 2);
                    Mot chou = new Mot(0, "chou", "chou", "", 2);
                    Mot tomate = new Mot(0, "tomate", "tomate", "", 2);

                    Mot pomme = new Mot(0, "pomme", "pomme", "", 1);
                    Mot poire = new Mot(0, "poire", "poire", "", 1);
                    Mot kiwi = new Mot(0, "kiwi", "kiwi", "", 1);
                    Mot citron = new Mot(0, "citron", "citron", "", 1);
                    Mot noix = new Mot(0, "noix", "noix", "", 1);
                    Mot cerise = new Mot(0, "cerise", "cerise", "", 1);
                    Mot fraise = new Mot(0, "fraise", "fraise", "", 1);
                    Mot melon = new Mot(0, "melon", "melon", "", 1);
                    Mot figue = new Mot(0, "figue", "figue", "", 1);

                    motDao.insert(tong);
                    motDao.insert(four);
                    motDao.insert(pain);
                    motDao.insert(gomme);
                    motDao.insert(stylo);
                    motDao.insert(slip);
                    motDao.insert(ballon);
                    motDao.insert(maison);
                    motDao.insert(verre);
                    motDao.insert(table);
                    motDao.insert(pied);
                    motDao.insert(main);
                    motDao.insert(poisson);
                    motDao.insert(zebre);
                    motDao.insert(ours);
                    motDao.insert(loup);
                    motDao.insert(porc);
                    motDao.insert(chevre);
                    motDao.insert(mouton);
                    motDao.insert(vache);
                    motDao.insert(cheval);
                    motDao.insert(lapin);
                    motDao.insert(souris);
                    motDao.insert(chat);
                    motDao.insert(chien);
                    motDao.insert(poule);
                    motDao.insert(courge);
                    motDao.insert(celeri);
                    motDao.insert(mais);
                    motDao.insert(figue);
                    motDao.insert(melon);
                    motDao.insert(fraise);
                    motDao.insert(cerise);
                    motDao.insert(radis);
                    motDao.insert(endive);
                    motDao.insert(navet);
                    motDao.insert(oignon);
                    motDao.insert(chou);
                    motDao.insert(noix);
                    motDao.insert(citron);
                    motDao.insert(kiwi);
                    motDao.insert(poire);
                    motDao.insert(pomme);
                    motDao.insert(tomate);
                    motDao.insert(lion);

                    return null;
                }


            }.execute(INSTANCE);
        }
    };
}











