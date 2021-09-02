package com.example.test.retrofit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0018\u00010\u0003H\'\u00a8\u0006\u0006"}, d2 = {"Lcom/example/test/retrofit/ApiInterface;", "", "getToDoList", "Lretrofit2/Call;", "", "Lcom/example/test/model/ToDoListItem;", "app_debug"})
public abstract interface ApiInterface {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "todos/")
    public abstract retrofit2.Call<java.util.List<com.example.test.model.ToDoListItem>> getToDoList();
}