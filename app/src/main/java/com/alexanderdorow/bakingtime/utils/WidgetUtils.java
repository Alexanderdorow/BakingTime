package com.alexanderdorow.bakingtime.utils;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.widget.WidgetProvider;

public class WidgetUtils {

    public static void updateWidget(Activity activity) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(activity);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(activity, WidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_step_list);
        WidgetProvider.updateWidgets(activity, appWidgetManager, appWidgetIds);
    }
}
