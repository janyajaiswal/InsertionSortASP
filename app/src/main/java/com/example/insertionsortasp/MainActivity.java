package com.example.insertionsortasp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputNumbers = findViewById(R.id.input_numbers);
        Button sortButton = findViewById(R.id.sort_button);
        Button quitButton = findViewById(R.id.quit_button);
        Button clearButton = findViewById(R.id.clear_button);
        TextView sortedResult = findViewById(R.id.sorted_result);
        TextView intermediateSteps = findViewById(R.id.intermediate_steps);

        sortButton.setOnClickListener(v -> {
            String input = inputNumbers.getText().toString();
            if (TextUtils.isEmpty(input)) {
                Toast.makeText(this, "Please enter numbers to sort", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input
            String[] numberStrings = input.split("\\s+");
            ArrayList<Integer> numbers = new ArrayList<>();
            try {
                for (String num : numberStrings) {
                    int value = Integer.parseInt(num);
                    if (value < 0 || value > 9) {
                        throw new IllegalArgumentException("Numbers must be between 0 and 9.");
                    }
                    numbers.add(value);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input: Numbers must be between 0 and 9.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate array size
            if (numbers.size() < 3 || numbers.size() > 8) {
                Toast.makeText(this, "Input size must be between 3 and 8.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert list to array
            int[] array = numbers.stream().mapToInt(i -> i).toArray();

            // Perform insertion sort and display intermediate steps
            StringBuilder steps = new StringBuilder("Input Array: " + arrayToString(array) + "\n");
            performInsertionSort(array, steps);

            // Display results
            intermediateSteps.setText(steps.toString());
            sortedResult.setText("Sorted Array: " + arrayToString(array));
        });

        // Clear Button functionality
        clearButton.setOnClickListener(v -> {
            inputNumbers.setText(""); // Clear input
            intermediateSteps.setText(""); // Clear intermediate steps
            sortedResult.setText(""); // Clear sorted result
        });

        // Quit Button functionality
        quitButton.setOnClickListener(v -> finish());
    }

    private void performInsertionSort(int[] arr, StringBuilder steps) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;

            // Append the current state of the array to steps
            steps.append(arrayToString(arr)).append("\n");
        }
    }

    private String arrayToString(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int num : arr) {
            result.append(num).append(" ");
        }
        return result.toString().trim();
    }
}
