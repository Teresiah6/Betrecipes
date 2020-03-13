package com.example.android.foodrecipes;



import java.util.List;

public class Data {
    public List<Bet> data;

    public Data(List<Bet> data) {
        this.data = data;
    }

    public Data() {
    }

    public List<Bet> getData() {
        return data;
    }

    public void setData(List<Bet> data) {
        this.data = data;
    }
}


