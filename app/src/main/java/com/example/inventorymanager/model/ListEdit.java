package com.example.inventorymanager.model;

public interface ListEdit<T>{
    void deleteListElement(int index);
    void deleteEntireList();
    void addListElement(T t);

    void clickAddButton();
    void clickEditButton();
}
