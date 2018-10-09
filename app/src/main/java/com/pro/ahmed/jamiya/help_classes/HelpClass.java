package com.pro.ahmed.jamiya.help_classes;

import android.content.Context;
import android.content.Intent;

import com.pro.ahmed.jamiya.SignUpActivity;

public class HelpClass {

    public static void startNewActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        if (className.equals(SignUpActivity.class)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);


    }

}
