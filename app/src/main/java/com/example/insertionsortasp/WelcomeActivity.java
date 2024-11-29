package com.example.insertionsortasp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Find the button
        Button startButton = findViewById(R.id.start_button);

        // Set OnClickListener
        startButton.setOnClickListener(v -> {
            // Navigate to MainActivity
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: Prevent going back to WelcomeActivity
        });
    }
}
