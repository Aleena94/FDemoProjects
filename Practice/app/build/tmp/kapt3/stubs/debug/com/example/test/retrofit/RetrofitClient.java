package com.example.test.retrofit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/test/retrofit/RetrofitClient;", "", "()V", "BASE_URL", "", "retrofitClient", "Lretrofit2/Retrofit;", "getRetrofitInstance", "app_debug"})
public final class RetrofitClient {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.test.retrofit.RetrofitClient INSTANCE = null;
    private static java.lang.String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static retrofit2.Retrofit retrofitClient;
    
    private RetrofitClient() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final retrofit2.Retrofit getRetrofitInstance() {
        return null;
    }
}