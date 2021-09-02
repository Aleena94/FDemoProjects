package com.example.test.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0002./B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020\u0015H\u0016J\b\u0010\'\u001a\u00020\u0015H\u0016J\b\u0010(\u001a\u00020)H\u0002J\u0012\u0010*\u001a\u00020)2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020)H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/example/test/view/ToDoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/paginate/Paginate$Callbacks;", "()V", "adapter", "Lcom/example/test/adapter/ToDoAdapter;", "binding", "Lcom/example/test/databinding/ActivityTodoBinding;", "getBinding", "()Lcom/example/test/databinding/ActivityTodoBinding;", "setBinding", "(Lcom/example/test/databinding/ActivityTodoBinding;)V", "context", "Landroid/content/Context;", "fakeCallback", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "loading", "", "networkDelay", "", "page", "", "paginate", "Lcom/paginate/Paginate;", "toDoViewModel", "Lcom/example/test/viewmodel/ToDoViewModel;", "getToDoViewModel", "()Lcom/example/test/viewmodel/ToDoViewModel;", "toDoViewModel$delegate", "Lkotlin/Lazy;", "todoItem", "", "Lcom/example/test/model/ToDoListItem;", "totalPages", "hasLoadedAllItems", "isLoading", "loadToDo", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLoadMore", "CustomLoadingListItemCreator", "VH", "app_debug"})
public final class ToDoActivity extends androidx.appcompat.app.AppCompatActivity implements com.paginate.Paginate.Callbacks {
    public com.example.test.databinding.ActivityTodoBinding binding;
    private androidx.recyclerview.widget.LinearLayoutManager layoutManager;
    private com.example.test.adapter.ToDoAdapter adapter;
    private java.util.List<com.example.test.model.ToDoListItem> todoItem;
    private boolean loading = false;
    private int page = 1;
    private android.os.Handler handler;
    private com.paginate.Paginate paginate;
    private int totalPages = 10;
    private long networkDelay = 2000L;
    private android.content.Context context;
    private final kotlin.Lazy toDoViewModel$delegate = null;
    private final java.lang.Runnable fakeCallback = null;
    
    public ToDoActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.test.databinding.ActivityTodoBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.example.test.databinding.ActivityTodoBinding p0) {
    }
    
    private final com.example.test.viewmodel.ToDoViewModel getToDoViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadToDo() {
    }
    
    @java.lang.Override()
    public void onLoadMore() {
    }
    
    @java.lang.Override()
    public boolean isLoading() {
        return false;
    }
    
    @java.lang.Override()
    public boolean hasLoadedAllItems() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0016\u00a8\u0006\r"}, d2 = {"Lcom/example/test/view/ToDoActivity$CustomLoadingListItemCreator;", "Lcom/paginate/recycler/LoadingListItemCreator;", "()V", "onBindViewHolder", "", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_debug"})
    static final class CustomLoadingListItemCreator implements com.paginate.recycler.LoadingListItemCreator {
        
        public CustomLoadingListItemCreator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/test/view/ToDoActivity$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "tvLoading", "Landroid/widget/TextView;", "getTvLoading", "()Landroid/widget/TextView;", "setTvLoading", "(Landroid/widget/TextView;)V", "app_debug"})
    public static final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView tvLoading;
        
        public VH(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvLoading() {
            return null;
        }
        
        public final void setTvLoading(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
    }
}