package com.kamila.bloodpressurebodytemperaturecontrol.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.kamila.bloodpressurebodytemperaturecontrol.config.ConfigFirebase;

public class User {


    private String name;
    private String email;
    private String password;
    private String idUser;
    private double bodyTemperature = 00.0;
    private double bloodPressure = 000.00;


    public User() {
    }

    //this method will Save the User on Firebase
    public void save(){
       DatabaseReference firebase = ConfigFirebase.getFirebaseDataBase();
       firebase.child("users")
               .child(this.idUser)
               .setValue(this);
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    @Exclude // this data will not be saved on the firebase
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude // this data will not be saved on the firebase
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
