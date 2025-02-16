package com.ian.rutineappgym.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ian.rutineappgym.model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insertAll(List<Exercise> exercises);

    @Query("SELECT * FROM exercises LIMIT 3")
    List<Exercise> getFirstThreeExercises();

}
