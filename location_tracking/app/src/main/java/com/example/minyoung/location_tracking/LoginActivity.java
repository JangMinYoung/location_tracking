package com.example.minyoung.location_tracking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.kakao.auth.Session;
import com.facebook.login.widget.LoginButton;
//import com.kakao.usermgmt.LoginButton;


import java.util.List;

public class LoginActivity extends AppCompatActivity {
//    private Button singup;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    //카카오톡 로그인을 위한 변수
    private Button btn_custom_login;
    private com.kakao.usermgmt.LoginButton btn_kakao_login;
    private SessionCallback callback;
    //페이스북 로그인을 위한 변수
    private static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private com.facebook.login.widget.LoginButton btn_facebook_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseRemoteConfig=FirebaseRemoteConfig.getInstance();
        String splash_background=mFirebaseRemoteConfig.getString("splash_background");

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.parseColor(splash_background));
        }
        //카카오톡 연동
        callback = new SessionCallback(); //세션콜백을 부르고
        Session.getCurrentSession().addCallback(callback); // 추가시키면 끝입니다!!
        btn_custom_login = (Button) findViewById(R.id.btn_custom_login);
        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kakao_login.performClick();
            }
        });
        btn_kakao_login = (com.kakao.usermgmt.LoginButton) findViewById(R.id.btn_kakao_login);

        //페이스북 연동
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        btn_facebook_login = findViewById(R.id.btn_facebook_login);

        btn_facebook_login.setReadPermissions("email");
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d(TAG, "onSucces LoginResult=" + loginResult);
                        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d(TAG, "onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d(TAG, "onError");
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
