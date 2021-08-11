package com.kamila.bloodpressurebodytemperaturecontrol.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kamila.bloodpressurebodytemperaturecontrol.R;
import com.kamila.bloodpressurebodytemperaturecontrol.helper.DateCustom;
import com.kamila.bloodpressurebodytemperaturecontrol.model.Numbers;

public class BodyTemperatureActivity2 extends AppCompatActivity {

    private TextInputEditText fieldDate;
    private EditText fieldBodyT;
    private Numbers numbers;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_temperature2);

        fieldBodyT = findViewById(R.id.editBodyT);
        fieldDate = findViewById(R.id.editDate);

        fieldDate.setText(DateCustom.currentDate());//this line of code will fill up the date field, with the current date
    }

    //this method will save the BloodPressure input
    public void saveBodyTemperature(View view){

        if(validateBodyTemperatureField()){
            numbers = new Numbers();
            String date = fieldDate.getText().toString();
            numbers.setValue(Double.parseDouble(fieldBodyT.getText().toString()));
            numbers.setDate(date);
            numbers.save(date);

        }
    }

    //this method will validate the fields in the
    public boolean validateBodyTemperatureField(){

        String textValue = fieldBodyT.getText().toString();
        String textDate = fieldDate.getText().toString();
        if(textValue.isEmpty()){
            if(textDate.isEmpty()){
                return true;

            }else{
                Toast.makeText(BodyTemperatureActivity2.this, "Please, fill up the date", //Toast displays a message to user fill up the date
                        Toast.LENGTH_LONG).show();
                return false;
            }

        }else{
            Toast.makeText(BodyTemperatureActivity2.this, "Please, fill up the numbers", //Toast displays a message to user fill up the value
                    Toast.LENGTH_LONG).show();
            return false;

        }

    }

}