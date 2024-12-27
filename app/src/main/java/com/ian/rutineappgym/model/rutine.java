package com.ian.rutineappgym.model;

import java.util.ArrayList;

public class rutine {
    private String name;
    private ArrayList<exercise> exercisesList=new ArrayList<> ();

    public rutine(String name,ArrayList<exercise> exercisesList){
        this.name=name;
        this.exercisesList=exercisesList;
    }


}
