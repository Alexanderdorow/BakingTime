package com.alexanderdorow.bakingtime;

import com.alexanderdorow.bakingtime.main.MainPresenterImpl;
import com.alexanderdorow.bakingtime.menu.MenuPresenterImpl;
import com.alexanderdorow.bakingtime.widget.WidgetProvider;
import com.alexanderdorow.bakingtime.widget.WidgetViewFactory;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MenuPresenterImpl presenter);

    void inject(MainPresenterImpl presenter);

    void inject(WidgetViewFactory widgetViewFactory);

    void inject(WidgetProvider widgetProvider);
}
