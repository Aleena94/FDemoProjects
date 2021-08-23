package com.example.demo.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PaginatedAdapter<ITEM, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final List<ITEM> mDataSet = new ArrayList<>();
    private OnPaginationListener mListener;
    private int mCurrentPage = 1;
    private RecyclerView mRecyclerView;
    private boolean loadingNewItems = true;


    @NonNull
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(@NonNull VH holder, int position);

    public int getItemCount() {
        return mDataSet.size();
    }

    public void submitItems(Collection<? extends ITEM> collection) {
        mDataSet.addAll(collection);
        if (mListener != null) {
            mListener.onCurrentPage(mCurrentPage);
            int mPageSize = 20;
            if (collection.size() == mPageSize) {
                loadingNewItems = false;
            } else {
                mListener.onFinish();
            }
        }
    }

    public int getStartPage() {
        return 1;
    }

    public void setDefaultRecyclerView(Activity activity, int recyclerViewId) {
        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        this.mRecyclerView = recyclerView;
        initPaginating();
        setAdapter();
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(this);
    }


    private void initPaginating() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisible = layoutManager.findLastVisibleItemPosition();
                    boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;
                    if (totalItemCount > 0 && endHasBeenReached) {
                        if (mListener != null) {
                            if (!loadingNewItems) {
                                loadingNewItems = true;
                                mListener.onNextPage(++mCurrentPage);
                            }
                        }
                    }
                }
            }
        });
    }

    public void setOnPaginationListener(OnPaginationListener onPaginationListener) {
        this.mListener = onPaginationListener;
    }

    public interface OnPaginationListener {
        void onCurrentPage(int page);

        void onNextPage(int page);

        void onFinish();
    }
}
