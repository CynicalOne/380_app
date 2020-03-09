package com.example.inventorymanager;

public interface ListEdit<T>{
    void deleteListElement(int index);
    void deleteEntireList();
    void addListElement(<E> e);

    void clickAddButton();
    void clickEditButton();
}
