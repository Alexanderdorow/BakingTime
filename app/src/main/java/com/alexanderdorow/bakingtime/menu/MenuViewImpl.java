package com.alexanderdorow.bakingtime.menu;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.base.BaseFragmentViewImpl;
import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.main.MainViewImpl;
import com.alexanderdorow.bakingtime.utils.WidgetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuViewImpl extends BaseFragmentViewImpl<MenuPresenter> implements MenuView, AdapterMenu.MenuClickListener {

    private AdapterMenu adapter;

    @BindView(R.id.rv_menu)
    public
    RecyclerView menu;

    @BindView(R.id.pb_loading)
    ProgressBar loading;

    @BindView(R.id.tv_error)
    TextView error;

    @Override
    public void initializeView() {
        adapter = new AdapterMenu(this);
        menu.setAdapter(adapter);
        menu.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_menu;
    }

    @Override
    public MenuPresenter getPresenter() {
        return new MenuPresenterImpl(this);
    }

    @Override
    public void setItemsOnAdapter(List<Recipe> body) {
        adapter.setItems(body);
    }

    @Override
    public void goToMainActivity() {
        WidgetUtils.updateWidget(getActivity());
        Intent intent = new Intent(getActivity(), MainViewImpl.class);
        startActivity(intent);
    }

    @Override
    public void onMenuItemClicked(Recipe recipe) {
        presenter.onMenuItemClicked(recipe);
    }

    @Override
    public void showLoading(boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
        menu.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showError(boolean show) {
        error.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.tv_error)
    public void onErrorClick() {
        presenter.requestRecipes();
    }

}
