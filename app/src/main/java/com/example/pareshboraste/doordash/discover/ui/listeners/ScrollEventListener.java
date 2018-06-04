package com.example.pareshboraste.doordash.discover.ui.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ScrollEventListener extends RecyclerView.OnScrollListener {
    private int currentTotal;
    private boolean isLoading;
    private OnLoadListener onLoadListener;

    public ScrollEventListener(int currentSize) {
        this.currentTotal = currentSize;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (isLoading) {
            if (totalItemCount > currentTotal) {
                isLoading = false;
                currentTotal = totalItemCount;
            }
        }
        int visibleThreshold = 2;
        if (!isLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            onLoadListener.load();
            isLoading = true;
        }
    }

    public void attachOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
}
