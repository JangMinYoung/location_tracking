package com.example.minyoung.location_tracking;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends Activity {
    private ImageView location_img;
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        start();

    }
    private void initView(){
        location_img= (ImageView) findViewById(R.id.location_img);
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
        location_img.setAnimation(anim);

    }
    private  void start(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        },2000);
    }



}
