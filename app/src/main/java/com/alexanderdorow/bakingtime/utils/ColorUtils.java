package com.alexanderdorow.bakingtime.utils;

import android.content.Context;
import android.os.Build;

public class ColorUtils {

    public static int getColor(int colorId, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorId);
        } else {
            return context.getResources().getColor(colorId);
        }
    }
}
