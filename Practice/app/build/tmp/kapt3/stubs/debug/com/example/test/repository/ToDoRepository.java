package com.example.test.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bJ\u001f\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/test/repository/ToDoRepository;", "", "retroClient", "Lcom/example/test/retrofit/RetrofitClient;", "dao", "Lcom/example/test/database/Dao;", "(Lcom/example/test/retrofit/RetrofitClient;Lcom/example/test/database/Dao;)V", "todoList", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/test/model/ToDoListItem;", "getToDo", "insertData", "", "todoItem", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ToDoRepository {
    private final com.example.test.retrofit.RetrofitClient retroClient = null;
    private final com.example.test.database.Dao dao = null;
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.test.model.ToDoListItem>> todoList;
    
    public ToDoRepository(@org.jetbrains.annotations.NotNull()
    com.example.test.retrofit.RetrofitClient retroClient, @org.jetbrains.annotations.NotNull()
    com.example.test.database.Dao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.test.model.ToDoListItem>> getToDo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.test.model.ToDoListItem> todoItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}