package com.example.db_view_ex;

import android.icu.text.Transliterator;

import java.util.ArrayList;

public class position {

    public String position;
    public ArrayList<String> comments = new ArrayList<String>();

    public position(String position){
        this.position = position;
    }

    public String toString(){
        return position;
    }

}
