package com.example.test.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0007\u0018\u00010\u0006J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0007\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/test/viewmodel/ToDoViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/test/repository/ToDoRepository;", "(Lcom/example/test/repository/ToDoRepository;)V", "toDoData", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/test/model/ToDoModel;", "toDoList", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/test/model/ToDoListItem;", "getToDo", "insertData", "", "todoItem", "app_debug"})
public final class ToDoViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.test.repository.ToDoRepository repository = null;
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.test.model.ToDoListItem>> toDoList;
    private androidx.lifecycle.LiveData<java.util.List<com.example.test.model.ToDoModel>> toDoData;
    
    public ToDoViewModel(@org.jetbrains.annotations.NotNull()
    com.example.test.repository.ToDoRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.test.model.ToDoListItem>> getToDo() {
        return null;
    }
    
    public final void insertData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.test.model.ToDoListItem> todoItem) {
    }
}