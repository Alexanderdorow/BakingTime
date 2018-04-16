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

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.alexanderdorow.bakingtime.AppApplication;
import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.daos.RecipeDao;
import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.main.MainViewImpl;
import com.alexanderdorow.bakingtime.utils.ColorUtils;

import javax.inject.Inject;

public class WidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    @Inject
    public RecipeDao recipeDao;
    @Inject
    public Context context;

    public WidgetViewFactory() {
        AppApplication.getComponent().inject(this);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        Recipe selectedRecipe = recipeDao.getSelectedRecipe();
        return selectedRecipe != null ? selectedRecipe.getSteps().size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.widget_row);
        Step step = recipeDao.getSelectedRecipe().getSteps().get(position);
        row.setTextViewText(R.id.widget_step_description, step.getShortDescription());
        Intent i = new Intent(context, MainViewImpl.class);
        i.putExtra(WidgetProvider.EXTRA_STEP, step);
        row.setOnClickFillInIntent(R.id.widget_item_container, i);
        row.setInt(R.id.widget_item_container, "setBackgroundColor",
                step.isSelected() ?
                        ColorUtils.getColor(R.color.colorPrimary, context) :
                        ColorUtils.getColor(R.color.white, context));

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return (null);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        Log.i("aldrow", "changed");
    }
}