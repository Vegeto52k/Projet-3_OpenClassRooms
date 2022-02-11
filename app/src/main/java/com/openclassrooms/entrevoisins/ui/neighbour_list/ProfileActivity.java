package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_avatar)
    ImageView profileAvatar;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_back)
    ImageButton profilBackButton;
    @BindView(R.id.profile_favorite_off)
    FloatingActionButton profile_favorite_off;
    @BindView(R.id.profile_favorite_on)
    FloatingActionButton profile_favorite_on;
    @BindView(R.id.profile_view1_name)
    TextView profileViewName;
    @BindView(R.id.profile_view1_adress)
    TextView profileViewAdress;
    @BindView(R.id.profile_view1_phone)
    TextView profileViewPhone;
    @BindView(R.id.profile_view1_social)
    TextView profileViewSocial;
    @BindView(R.id.profile_about)
    TextView profileAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        ButterKnife.bind(this);


        profileView();
        setFavorite();

        setProfilBackButton();
    }

    private void setProfilBackButton() {
        profilBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void profileView() {

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("Neighbour");

        String avatar = neighbour.getAvatarUrl();
        String name1 = neighbour.getName();
        String name2 = neighbour.getName();
        String adress = neighbour.getAddress();
        String phone = neighbour.getPhoneNumber();
        String about = neighbour.getAboutMe();


        Glide.with(this).load(avatar).into(profileAvatar);
        profileName.setText(name1);
        profileViewName.setText(name2);
        profileViewAdress.setText(adress);
        profileViewPhone.setText(phone);
        profileAbout.setText(about);

    }


    public void setFavorite() {

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("Neighbour");

        if (neighbour.isFavorite() == true) {
            profile_favorite_off.hide();
            profile_favorite_on.show();
        } else {
            profile_favorite_off.show();
            profile_favorite_on.hide();
        }

        profile_favorite_on.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                neighbour.setFavorite(false);
                DI.getNeighbourApiService().setFavorite(neighbour);
                Log.d("Test", "retiré des favoris");
                profile_favorite_off.show();
                profile_favorite_on.hide();
            }
        }));

        profile_favorite_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                neighbour.setFavorite(true);
                DI.getNeighbourApiService().setFavorite(neighbour);
                Log.d("Test", "ajouté aux favoris");
                profile_favorite_off.hide();
                profile_favorite_on.show();
            }
        });
    }

}
