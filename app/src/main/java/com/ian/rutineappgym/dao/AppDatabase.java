package com.ian.rutineappgym.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ian.rutineappgym.model.Converters;
import com.ian.rutineappgym.model.Exercise;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import android.content.Context;

@Database(entities = {Exercise.class}, version = 4) // 🚨 Asegúrate de cambiar la versión
@TypeConverters(Converters.class) // 🚨 Esto es obligatorio para que Room acepte List<String>
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "exercise_database")
                            .fallbackToDestructiveMigration() // 🔥 Borra la base de datos si la estructura cambia
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}