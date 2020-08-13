package com.example.devinet.bo;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Classe representant le mot a trouver
 */
@Entity(foreignKeys= @ForeignKey(entity = Categorie.class,
        parentColumns = "idCat",
        childColumns = "idCat"),
        indices = {@Index("idCat")}
)
public class Mot implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int idMot;
    private String img;
    private String mot;
    private String proposition;
    private int idCat;

    public Mot(int idMot, String img, String mot, String proposition, int idCat) {
        this.idMot = idMot;
        this.img = img;
        this.mot = mot;
        this.proposition = proposition;
        this.idCat = idCat;
    }

    protected Mot(Parcel in) {
        idMot = in.readInt();
        img = in.readString();
        mot = in.readString();
        proposition = in.readString();
        idCat = in.readInt();
    }

    public static final Creator<Mot> CREATOR = new Creator<Mot>() {
        @Override
        public Mot createFromParcel(Parcel in) {
            return new Mot(in);
        }

        @Override
        public Mot[] newArray(int size) {
            return new Mot[size];
        }
    };

    public int getIdMot() {
        return idMot;
    }

    public void setIdMot(int idMot) {
        this.idMot = idMot;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idMot);
        parcel.writeString(img);
        parcel.writeString(mot);
        parcel.writeString(proposition);
        parcel.writeInt(idCat);
    }
}

