package com.example.pareshboraste.doordash.discover.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.pareshboraste.doordash.discover.model.Restaurant;
import com.example.pareshboraste.doordash.discover.ui.listeners.OnLoadListener;
import com.example.pareshboraste.doordash.discover.ui.views.DiscoverViewContract;
import com.example.pareshboraste.doordash.discover.usecase.DiscoverUseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DiscoverViewModel extends ViewModel implements OnLoadListener {

    private final static String lat = "37.422740";
    private final static String lng = "-122.139956";
    private final static int PAGE_SIZE = 50;
    private int start;

    private DiscoverUseCase discoverUseCase;
    public MutableLiveData<List<Restaurant>> restaurants = new MutableLiveData<>();

    private DiscoverViewContract view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    public DiscoverViewModel(DiscoverUseCase discoverUseCase) {
        this.discoverUseCase = discoverUseCase;

    }

    private void getList() {
        Observable<List<Restaurant>> observable = discoverUseCase.getListDiscover(lat, lng, start, start + PAGE_SIZE);
        view.showLoading(true);
        compositeSubscription.add(observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Restaurant>>() {
                    @Override
                    public void onCompleted() {
                        // no-op
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("#error-DiscoverList", e.getMessage());
                        view.showErrorMessage();
                        view.showLoading(false);
                    }

                    @Override
                    public void onNext(List<Restaurant> list) {
                        updateList(list);
                        view.showLoading(false);
                    }
                }));

    }

    private void updateList(List<Restaurant> list) {
        start = start + list.size();
        if (restaurants.getValue() != null)
            list.addAll(0, restaurants.getValue());
        restaurants.setValue(list);
    }

    public int getCurrentSize() {
        if (restaurants.getValue() == null) return 0;
        return restaurants.getValue().size();
    }

    public void attachView(DiscoverViewContract view) {
        this.view = view;
    }

    public void detachViewModel() {
        compositeSubscription.clear();
    }

    @Override
    public void load() {
        getList();
    }
}
