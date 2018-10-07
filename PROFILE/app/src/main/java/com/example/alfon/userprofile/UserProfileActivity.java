package com.example.alfon.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class UserProfileActivity extends AppCompatActivity {

    private UserProfile user_profile;
    private ImageView background_image;
    private ImageView profile_image;

    private TextView name_user;
    private TextView user;
    private TextView following_user;
    private TextView follower_user;
    private TextView about_user;
    private Gson gson;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        try {
            InputStream stream = getAssets().open("user.json");
            InputStreamReader reader = new InputStreamReader(stream);
            user_profile = gson.fromJson(reader, UserProfile.class);
        } catch (IOException e) {
            Toast.makeText(this,"NO FUNCIONA",Toast.LENGTH_SHORT).show();
        }

        background_image = findViewById(R.id.backgroundImage);
        profile_image = findViewById(R.id.imageView);
        Glide.with(this)
                .load("file:///android_asset/trafalgar.png")
                .into(background_image);
        Glide.with(this)
                .load("file:///android_asset/death_note.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(profile_image);

        name_user = findViewById(R.id.nameText);
        user = findViewById(R.id.userText);
        following_user = findViewById(R.id.followingNumbers);
        follower_user = findViewById(R.id.followersNumber);
        about_user = findViewById(R.id.AboutMeDescription);


        UpdateMovie();

    }

    private void UpdateMovie(){

        name_user.setText(user_profile.getNameUser());
        user.setText(user_profile.getUser());

        follower_user.setText(user_profile.getFollowers());
        following_user.setText(user_profile.getFollowinguser());
        about_user.setText(user_profile.getAbout());

    }
}

