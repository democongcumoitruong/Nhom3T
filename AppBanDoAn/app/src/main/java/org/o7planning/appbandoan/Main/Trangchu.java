package org.o7planning.appbandoan.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.o7planning.appbandoan.R;

import java.util.ArrayList;
import java.util.List;

public class Trangchu extends AppCompatActivity {

    NavigationView navigationView;

    ViewFlipper viewFlipper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        Anhxa();
        RunviewFlipper();
    }

    private void Anhxa() {
        navigationView = findViewById(R.id.naviga);
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    private void RunviewFlipper() {
        List<String> dsmonan = new ArrayList<>();
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/stylish-cool-food-burger-restaurant-web-banner_6022760.jpg!w700wp");
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/retro-simple-chinese-meal-noodles-web-banner_6022757.jpg!w700wp");
        dsmonan.add("https://img.pikbest.com/backgrounds/20210619/fashion-color-gradient-hot-dog-food-web-banner_6022753.jpg!w700wp");
        for(int i =0;i< dsmonan.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(dsmonan.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setInAnimation(out);
    }
}