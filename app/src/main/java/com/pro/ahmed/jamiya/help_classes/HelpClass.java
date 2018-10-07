package com.pro.ahmed.jamiya.help_classes;

import android.content.Context;
import android.content.Intent;

public class HelpClass {

    public static void startNewActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }

}
