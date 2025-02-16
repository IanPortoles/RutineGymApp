package com.ian.rutineappgym.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ian.rutineappgym.dao.*;
import com.ian.rutineappgym.databinding.FragmentHomeBinding;
import com.ian.rutineappgym.model.Exercise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class HomeFragment extends Fragment {
    private AppDatabase db;
    private ExerciseDao exerciseDao;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar Base de Datos
        db = AppDatabase.getInstance(requireContext());
        exerciseDao = db.exerciseDao();

        // Cargar datos desde JSON si la base de datos está vacía
        loadExercisesFromJson();

        return root;
    }


    private void loadFirstThreeExercises() {
        new Thread(() -> {
            List<Exercise> exercises = exerciseDao.getFirstThreeExercises();

            // Actualizar la UI en el hilo principal
            requireActivity().runOnUiThread(() -> {
                if (exercises != null && !exercises.isEmpty()) {
                    StringBuilder builder = new StringBuilder();
                    for (Exercise exercise : exercises) {
                        builder.append("📌 ").append(exercise.name).append("\n");
                    }
                    binding.textHome.setText(builder.toString());
                } else {
                    binding.textHome.setText("No hay ejercicios disponibles.");
                }
            });
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void loadExercisesFromJson() {
        new Thread(() -> {
            try {
                // Verificar si ya hay datos en la base de datos
                if (exerciseDao.getFirstThreeExercises().size() > 0) {
                    Log.d("DB", "La base de datos ya tiene ejercicios.");
                    return;
                }

                // Leer el archivo JSON desde assets
                InputStream is = requireContext().getAssets().open("exercises.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Exercise>>() {}.getType();
                List<Exercise> exercises = gson.fromJson(reader, listType);

                // Insertar los ejercicios en la base de datos
                exerciseDao.insertAll(exercises);
                Log.d("DB", "Insertados " + exercises.size() + " ejercicios.");

                requireActivity().runOnUiThread(this::loadFirstThreeExercises);
            } catch (Exception e) {
                Log.e("DB", "Error al leer el JSON", e);
            }
        }).start();
    }

}
