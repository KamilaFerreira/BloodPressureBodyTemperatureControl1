package com.kamila.bloodpressurebodytemperaturecontrol.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.kamila.bloodpressurebodytemperaturecontrol.R;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;
import com.kamila.bloodpressurebodytemperaturecontrol.helper.Base64Custom;
import com.kamila.bloodpressurebodytemperaturecontrol.model.User;

public class RegisterActivity extends AppCompatActivity {
    //atributes
    private EditText name;
    private EditText email;
    private EditText password;
    private Button btRegister;
    private FirebaseAuth authentication;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        /*this piece of code finds the components on the Interface = FindViewById*/
        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        btRegister = findViewById(R.id.buttonRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This piece of code will recover the data and pass to a String, afterwards will validate the fields
                String textName = name.getText().toString();
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                //here the code will validate if the User fill up all the fields, case the fields are not filled, the app will display a message
                if(!textName.isEmpty()){
                    if(!textEmail.isEmpty()){
                        if(!textPassword.isEmpty()){
                            //this piece of code creates a user
                            user = new User();
                            user.setName(textName);
                            user.setEmail(textEmail);
                            user.setPassword(textPassword);

                            registerUser();//this line of code calls the method to Register the User

                        }else{
                            Toast.makeText(RegisterActivity.this, "Please, fill up the Password", //Toast displays a message
                                    Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this, "Please, fill up the E-mail", //Toast displays a message
                                Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this, "Please, fill up the Name", //Toast displays a message
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //this method will register the User
    public void registerUser(){

        authentication = ConfigFirebase.getFirebaseAuthentication();//this piece of code Gets the object Firebase and allows to authenticate the User
        authentication.createUserWithEmailAndPassword( // this line will create a new user
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { // this piece of code verifies if the User was created
                if(task.isSuccessful()){
                    finish(); // this method closes the Register and goes to the Main Screen page

                    String idUser = Base64Custom.encodeBase64(user.getEmail());//this piece of code will Save the User's data
                    user.setIdUser(idUser);
                    user.save();
                /*This piece of code will treats the exception,
                such as: weak Password, Invalid e-mail, or existing User
                 */
                }else{
                    String exception = "";
                    try{
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        exception = "Please, type a Stronger Password!";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Please, type a valid E-mail!";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "The User is already Registered";
                    }catch (Exception e ){
                        exception = "Error to Register User" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegisterActivity.this, exception,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}