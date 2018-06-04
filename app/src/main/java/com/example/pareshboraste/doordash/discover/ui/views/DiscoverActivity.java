package com.example.pareshboraste.doordash.discover.ui.views;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pareshboraste.doordash.R;
import com.example.pareshboraste.doordash.discover.di.DoorDashSingleton;
import com.example.pareshboraste.doordash.discover.ui.adapter.RestaurantAdapter;
import com.example.pareshboraste.doordash.discover.ui.listeners.ScrollEventListener;
import com.example.pareshboraste.doordash.discover.viewmodel.DiscoverViewModel;
import com.example.pareshboraste.doordash.discover.viewmodel.DiscoverViewModelFactory;

import javax.inject.Inject;

public class DiscoverActivity extends AppCompatActivity implements DiscoverViewContract {

    @Inject
    DiscoverViewModelFactory discoverViewModelFactory;

    private DiscoverViewModel discoverViewModel;
    private RestaurantAdapter restaurantAdapter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DoorDashSingleton.getInstance().getComponent().inject(this);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        discoverViewModel = ViewModelProviders.of(this, discoverViewModelFactory).get(DiscoverViewModel.class);
        discoverViewModel.restaurants.observe(this, restaurants -> {
            restaurantAdapter.updateList(restaurants);
        });
        setupUI();
        setupRecyclerView();
        discoverViewModel.load();
    }

    private void setupUI() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setCancelable(false);
        discoverViewModel.attachView(this);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManger = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManger);
        restaurantAdapter = new RestaurantAdapter();
        recyclerView.setAdapter(restaurantAdapter);
        ScrollEventListener scrollEventListener = new ScrollEventListener(discoverViewModel.getCurrentSize());
        recyclerView.addOnScrollListener(scrollEventListener);
        scrollEventListener.attachOnLoadListener(discoverViewModel);
    }

    @Override
    public void showLoading(boolean showLoading) {
        if (showLoading) {
            progress.show();
        } else {
            progress.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        discoverViewModel.detachViewModel();
    }

    @Override
    public void showErrorMessage() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.generic_error_title)
                .setMessage(R.string.generic_error_message)
                .setPositiveButton(R.string.ok_confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }
}
