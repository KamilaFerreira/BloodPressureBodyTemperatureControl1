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

public class BloodPressureActivity2 extends AppCompatActivity {

    private TextInputEditText fieldDate;
    private EditText fieldBloodP;
    private Numbers numbers;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure2);

        fieldBloodP = findViewById(R.id.editBloodP);
        fieldDate = findViewById(R.id.editDate);

        fieldDate.setText(DateCustom.currentDate());//this line of code will fill up the date field, with the current date
    }
    //this method will save the BloodPressure input
    public void saveBloodPressure(View view){

        if(validateBloodPressureField()){
            numbers = new Numbers();
            String date = fieldDate.getText().toString();
            numbers.setValue(Double.parseDouble(fieldBloodP.getText().toString()));
            numbers.setDate(date);
            numbers.save(date);
            finish();

        }
    }

    //this method will validate the fields in the
    public boolean validateBloodPressureField(){

        String textValue = fieldBloodP.getText().toString();
        String textDate = fieldDate.getText().toString();
        if(!textValue.isEmpty()){
            if(!textDate.isEmpty()){
                return true;

            }else{
                Toast.makeText(BloodPressureActivity2.this, "Please, fill up the date", //Toast displays a message to user fill up the date
                        Toast.LENGTH_LONG).show();
                return false;
            }

        }else{
            Toast.makeText(BloodPressureActivity2.this, "Please, fill up the numbers", //Toast displays a message to user fill up the value
                    Toast.LENGTH_LONG).show();
            return false;

        }
    }
}