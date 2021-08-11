package com.kamila.bloodpressurebodytemperaturecontrol.helper;

import android.util.Base64;

public class Base64Custom {

    public static String encodeBase64(String text){//this will encode the User's e-email
        return Base64.encodeToString(text.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)", "");//the Method ReplaceAll will remove invalid characters

    }

    public static String decodeBase64( String encodeText){// this method will return the User's e-mail back to normal
       return new String(Base64.decode(encodeText, Base64.DEFAULT));//the return from the method will be in a String

    }
}
