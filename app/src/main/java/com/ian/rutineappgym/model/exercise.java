package com.ian.rutineappgym.model;

public class exercise {
   private String name;
    private String type;
   private  String equipament;
    private String difficulty;
    private String instructions;

    public  exercise(String name, String type,String equipament,String difficulty,String instructions){
        this.name=name;
        this.type=type;
        this.equipament=equipament;
        this.difficulty=difficulty;
        this.instructions=instructions;
    }

    public void setExercise(String name, String type,String equipament,String difficulty,String instructions){
        new exercise(name,type,equipament,difficulty,instructions);
    }
}
