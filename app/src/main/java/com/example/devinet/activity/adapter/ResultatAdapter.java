package com.example.devinet.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.devinet.R;
import com.example.devinet.bo.Mot;

import java.util.List;

public class ResultatAdapter extends ArrayAdapter<Mot>  {


    public ResultatAdapter(@NonNull Context context, int resource, @NonNull List<Mot> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View nouvelleLigne, @NonNull ViewGroup parent) {
        if (nouvelleLigne == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nouvelleLigne = li.inflate(R.layout.style_ligne_mot, parent, false);
        }

        TextView tvNom = nouvelleLigne.findViewById(R.id.tv_mot);
        tvNom.setText(getItem(position).getMot().toUpperCase());

        TextView tvProposition = nouvelleLigne.findViewById(R.id.tv_proposition);
        tvProposition.setText(getItem(position).getProposition());

//        ImageView ivPhoto = nouvelleLigne.findViewById(R.id.iv_photo);
//        ivPhoto.setImageURI(Uri.parse(getItem(position).getImg()));

        CheckBox chkCorrect = nouvelleLigne.findViewById(R.id.chk_result);
        if (getItem(position).getProposition().equals(getItem(position).getMot().toUpperCase())) {
            chkCorrect.setChecked(true);
        }
        return nouvelleLigne;
    }
}
