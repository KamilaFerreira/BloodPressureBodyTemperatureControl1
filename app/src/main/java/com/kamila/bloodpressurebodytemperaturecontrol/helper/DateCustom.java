package com.kamila.bloodpressurebodytemperaturecontrol.helper;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DateCustom {

    //this method will get the current date when the user input a new data

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String currentDate(){

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = simpleDateFormat.format(date);
        return dateString;

    }

    public static String monthYearDate(String date){

        String returnDate [] = date.split("/");
        String day = returnDate[0]; //ex: day 23
        String month = returnDate[1]; //ex: month 01
        String year = returnDate[2]; //ex: year 2021

        String monthYear = month + year;
        return monthYear;

    }

}
