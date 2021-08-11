package com.kamila.bloodpressurebodytemperaturecontrol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.kamila.bloodpressurebodytemperaturecontrol.PrincipalActivity;
import com.kamila.bloodpressurebodytemperaturecontrol.R;
import com.kamila.bloodpressurebodytemperaturecontrol.activity.LoginActivity;
import com.kamila.bloodpressurebodytemperaturecontrol.activity.RegisterActivity;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        setButtonBackVisible(false);//this line removes the navigating buttons on the mobile phone
        setButtonNextVisible(false);//this line removes the navigating buttons on the mobile phone

        /*this Fragment starts building a interface and allows
        creating and modify any part of the interface*/

        addSlide(new FragmentSlide.Builder()  // this Fragment starts building a interface through the methods bellow
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());
        addSlide(new FragmentSlide.Builder() // this Fragment starts building a interface through the methods bellow
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());
        addSlide(new FragmentSlide.Builder() // this Fragment starts building a interface through the methods bellow
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                //.canGoForward(false)
                .build());
        addSlide(new FragmentSlide.Builder() // this Fragment starts building a interface through the methods bellow
                .background(android.R.color.white)
                .fragment(R.layout.intro_register)
                //.canGoForward(false)
                .build());

    }


    @Override
    protected void onStart() {
        super.onStart();
        verifyUserLogged();
    }

    //this method allows the User to log in the account
    public void btLogIn(View view) {
        startActivity(new Intent(this, LoginActivity.class)); // the Intent object will start a function coded bellow

    }

    //this method allows the User to create ann account
    public void btRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class)); // the Intent object will start a function coded bellow


    }

    //this method verifies is the User is logged in the app
    public void verifyUserLogged() {
        authentication = ConfigFirebase.getFirebaseAuthentication();
        //authentication.signOut();
        if (authentication.getCurrentUser() != null) { //the Method CurrentUser verifies if the user is on the database
            openMainScreen(); //after the first verification, the method send the user to the main screen

        }


    }
    //this method Opens the Main Screen
    public void openMainScreen() {
        startActivity(new Intent(this, PrincipalActivity.class));


    }
}