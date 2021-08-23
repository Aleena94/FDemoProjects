package com.example.demo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.demo.adapter.PaginatedAdapter.OnPaginationListener
import android.view.ViewGroup
import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

abstract class PaginatedAdapter<ITEM, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {
    private val mDataSet: MutableList<ITEM> = ArrayList()
    private var mListener: OnPaginationListener? = null
    private var mCurrentPage = 1
    private var mRecyclerView: RecyclerView? = null
    private var loadingNewItems = true
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract override fun onBindViewHolder(holder: VH, position: Int)
    override fun getItemCount(): Int {
        return mDataSet.size
    }

    fun submitItems(collection: Collection<ITEM>) {
        mDataSet.addAll(collection)
        if (mListener != null) {
            mListener!!.onCurrentPage(mCurrentPage)
            val mPageSize = 20
            if (collection.size == mPageSize) {
                loadingNewItems = false
            } else {
                mListener!!.onFinish()
            }
        }
    }

    val startPage: Int
        get() = 1

    fun setDefaultRecyclerView(activity: Activity, recyclerViewId: Int) {
        val recyclerView: RecyclerView = activity.findViewById(recyclerViewId)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        mRecyclerView = recyclerView
        initPaginating()
        setAdapter()
    }

    private fun setAdapter() {
        mRecyclerView!!.adapter = this
    }

    private fun initPaginating() {
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (layoutManager != null) {
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val endHasBeenReached = lastVisible + 2 >= totalItemCount
                    if (totalItemCount > 0 && endHasBeenReached) {
                        if (mListener != null) {
                            if (!loadingNewItems) {
                                loadingNewItems = true
                                mListener!!.onNextPage(++mCurrentPage)
                            }
                        }
                    }
                }
            }
        })
    }

    fun setOnPaginationListener(onPaginationListener: OnPaginationListener?) {
        mListener = onPaginationListener
    }

    interface OnPaginationListener {
        fun onCurrentPage(page: Int)
        fun onNextPage(page: Int)
        fun onFinish()
    }
}