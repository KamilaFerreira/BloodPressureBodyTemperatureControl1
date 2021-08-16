package com.kamila.bloodpressurebodytemperaturecontrol.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.kamila.bloodpressurebodytemperaturecontrol.PrincipalActivity;
import com.kamila.bloodpressurebodytemperaturecontrol.R;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;
import com.kamila.bloodpressurebodytemperaturecontrol.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button buttonLogin;
    private User user;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();
                //here the code will validate if the User fill up all the fields, case the fields are not filled, the app will display a message
                if(!textEmail.isEmpty()){
                    if(!textPassword.isEmpty()){

                        user = new User();
                        user.setEmail(textEmail);
                        user.setPassword(textPassword);
                        validateLogin();
                        //this piece of code will validate the existing user

                    }else{
                        Toast.makeText(LoginActivity.this, "Please, fill up the Password", //Toast displays a message
                                Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Please, fill up the E-mail", //Toast displays a message
                            Toast.LENGTH_LONG).show();
                }


            }
        });

    }

        //this method will validate the existing User
         public void validateLogin(){

        authentication = ConfigFirebase.getFirebaseAuthentication();
        authentication.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openMainScreen();

                }else{
                    String exception = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e) {
                        exception = "User does not exist";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Email and Password does not exist";
                    }catch (Exception e ) {
                        exception = "Error to Register User" + e.getMessage();
                        e.printStackTrace();
                    }

                   Toast.makeText(LoginActivity.this,exception, // the Toast displyas the error message
                           Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    //this method Opens the Main Screen

    public void openMainScreen(){
        startActivity( new Intent(this, PrincipalActivity.class));
        finish(); //this method closes the Login Activity

    }
}