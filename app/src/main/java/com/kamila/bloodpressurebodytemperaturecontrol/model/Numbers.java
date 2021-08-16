package com.kamila.bloodpressurebodytemperaturecontrol.model;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;
import com.kamila.bloodpressurebodytemperaturecontrol.helper.Base64Custom;
import com.kamila.bloodpressurebodytemperaturecontrol.helper.DateCustom;

public class Numbers {

    private String date;
    private double value;

    public Numbers() {
    }

    //this method will save the input on the firebase
    public void save(String dateCurrent){
        FirebaseAuth authentication = ConfigFirebase.getFirebaseAuthentication();
        String idUser = Base64Custom.encodeBase64(authentication.getCurrentUser().getEmail());
        String monthYear = DateCustom.monthYearDate(dateCurrent);
        DatabaseReference firebase = ConfigFirebase.getFirebaseDataBase();
        firebase.child("numbers")
                .child(idUser)
                .child(monthYear)
                .push()
                .setValue(this);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}


