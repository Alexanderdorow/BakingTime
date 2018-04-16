/***
 Copyright (c) 2008-2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 From _The Busy Coder's Guide to Advanced Android Development_
 http://commonsware.com/AdvAndroid
 */


package com.alexanderdorow.bakingtime.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.alexanderdorow.bakingtime.AppApplication;
import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.daos.RecipeDao;
import com.alexanderdorow.bakingtime.main.MainViewImpl;

import javax.inject.Inject;

public class WidgetProvider extends AppWidgetProvider {
    public static String EXTRA_STEP = "com.alexanderdorow.bakingtime.widget.STEP";

    @Inject
    public RecipeDao recipeDao;

    public WidgetProvider() {
        AppApplication.getComponent().inject(this);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = getListRemoteView(context);
            if (recipeDao.getSelectedRecipe() == null) {
                views.setViewVisibility(R.id.widget_step_list, View.GONE);
                views.setViewVisibility(R.id.widget_no_recipe_warning, View.VISIBLE);
            }
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews rv = getListRemoteView(context);
            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
    }

    private static RemoteViews getListRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);
        Intent i = new Intent(context, MainViewImpl.class);
        views.setViewVisibility(R.id.widget_step_list, View.VISIBLE);
        views.setViewVisibility(R.id.widget_no_recipe_warning, View.GONE);
        views.setPendingIntentTemplate(R.id.widget_step_list, PendingIntent.getActivity(context, 0, i, 0));
        Intent intent = new Intent(context, WidgetService.class);
        views.setRemoteAdapter(R.id.widget_step_list, intent);
        return views;
    }
}