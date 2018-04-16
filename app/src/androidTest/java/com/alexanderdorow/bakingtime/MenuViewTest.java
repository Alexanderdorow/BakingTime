package com.alexanderdorow.bakingtime;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.alexanderdorow.bakingtime.home.HomeViewImpl;
import com.alexanderdorow.bakingtime.menu.AdapterMenu;
import com.alexanderdorow.bakingtime.menu.MenuViewImpl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4.class)
public class MenuViewTest {

    @Rule
    public ActivityTestRule<HomeViewImpl> mActivityTestRule = new ActivityTestRule<>(HomeViewImpl.class);


    @Test
    public void clickListViewItem_OpensHomeActivity() {
        onView(withId(R.id.fg_menu)).check(matches(isDisplayed()));
        MenuViewImpl menuView = (MenuViewImpl) mActivityTestRule.getActivity().getSupportFragmentManager().getFragments().get(0);
        AdapterMenu adapterMenu = (AdapterMenu) menuView.menu.getAdapter();
        adapterMenu.callback.onMenuItemClicked(adapterMenu.getItemAt(0));
        Espresso.pressBack();
    }
}
