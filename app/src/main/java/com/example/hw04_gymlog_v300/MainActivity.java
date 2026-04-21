package com.example.hw04_gymlog_v300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw04_gymlog_v300.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDataBase db;
    private ArrayAdapter<String> adapter;
    private final ArrayList<String> displayLogs = new ArrayList<>();
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!PreferenceManager.isLoggedIn(this)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDataBase.getInstance(this);
        currentUserId = PreferenceManager.getLoggedInUserId(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayLogs);
        binding.logsListView.setAdapter(adapter);

        loadLogs();

        // Add log
        binding.addButton.setOnClickListener(v -> {
            String exercise = binding.logEditText.getText().toString().trim();

            if (exercise.isEmpty()) {
                Toast.makeText(this, "Cannot insert empty log", Toast.LENGTH_SHORT).show();
                return;
            }

            db.gymLogDAO().insert(new GymLog(exercise, currentUserId));
            binding.logEditText.setText("");
            loadLogs();
        });

        // Sign out button
        binding.logoutButton.setOnClickListener(v -> {
            PreferenceManager.logout(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void loadLogs() {
        List<GymLog> logs = db.gymLogDAO().getLogsForUser(currentUserId);
        displayLogs.clear();

        for (GymLog log : logs) {
            displayLogs.add(log.toString());
        }

        adapter.notifyDataSetChanged();
    }
}