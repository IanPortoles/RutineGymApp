package com.ian.rutineappgym.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import java.util.List;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("force")
    public String force;

    @SerializedName("level")
    public String level;

    @SerializedName("mechanic")
    public String mechanic;

    @SerializedName("equipment")
    public String equipment;

    @SerializedName("category")
    public String category;

    @SerializedName("primaryMuscles")
    public List<String> primaryMuscles;

    @SerializedName("secondaryMuscles")
    public List<String> secondaryMuscles;

    @SerializedName("instructions")
    public List<String> instructions;

    @SerializedName("images")
    public List<String> images;

    public Exercise() {}
}

