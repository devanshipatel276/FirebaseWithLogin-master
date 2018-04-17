package com.example.devanshi.firebasewithlogin;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.devanshi.firebasewithlogin.util.Mypref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.devanshi.firebasewithlogin.util.Mypref.FLAG;
import static com.example.devanshi.firebasewithlogin.util.Mypref.mypreference;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private ImageView mIVimg;
    private  static  int flag=0;
    private View view;
    private SharedPreferences mSharedPreferences;
    private static final String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        makerequest();

        mIVimg = (ImageView)findViewById(R.id.imgLogo);


    }

    private void makerequest()
    {   if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA ) &&ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE ) && ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE ) && ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION  )){

    }

        ActivityCompat.requestPermissions(SplashActivity.this,
                new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CALL_PHONE, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                101);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (requestCode == 101) {
            if (grantResults.length > 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED || grantResults[3] != PackageManager.PERMISSION_GRANTED) {
                if (grantResults[0] == 0 && grantResults[1] == 0 && grantResults[2] == 0 && grantResults[3] == 0) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            final Mypref mypref = new Mypref();
                            mypref.SharedPreferenceUtils(SplashActivity.this);
                            mSharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

                            String val = (mypref.getStringValue(FLAG, ""));
                            if (val.equals("1")) {

                                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                                startActivity(i);


                            } else {

                                Intent i = new Intent(SplashActivity.this, SigninActivity.class);
                                startActivity(i);


                            }

                            finish();

                        }
                    }, SPLASH_TIME_OUT);
                }
                else {
                    makerequest();
                }
            }


        } else {

        }
    }

}


