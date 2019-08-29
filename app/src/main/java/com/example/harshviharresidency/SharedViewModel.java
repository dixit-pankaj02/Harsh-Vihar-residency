package com.example.harshviharresidency;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData selected = new MutableLiveData();

    public void setSelected(String item) {
        selected.setValue(item);
    }

    public LiveData getSelected() {
        return selected;
    }
}
