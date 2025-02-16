package com.ian.rutineappgym.model;

import java.util.ArrayList;

public class rutine {
    private String name;
    private ArrayList<Exercise> exercisesList=new ArrayList<> ();

    public rutine(String name,ArrayList<Exercise> exercisesList){
        this.name=name;
        this.exercisesList=exercisesList;
    }


}
