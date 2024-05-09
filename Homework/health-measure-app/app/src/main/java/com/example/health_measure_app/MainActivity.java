package com.example.health_measure_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText weightEditText, heightEditText;
    private Button calculateButton;
    private TextView bmiTextView, exerciseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        bmiTextView = findViewById(R.id.bmiTextView);
        exerciseTextView = findViewById(R.id.exerciseTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString()) / 100; // Convert height to meters
        double bmi = weight / (height * height);

        String bmiResult = String.format("BMI: %.2f", bmi);
        bmiTextView.setText(bmiResult);

        // Suggesting exercises based on BMI
        String exerciseSuggestion;
        if (bmi < 18.5) {
            exerciseSuggestion = "Bạn cần tăng cân và tập thể dục đều đặn.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            exerciseSuggestion = "Bạn có thể giữ nguyên cân nặng hiện tại và tập thể dục để duy trì sức khỏe.";
        } else {
            exerciseSuggestion = "Bạn có thể cần giảm cân và tập thể dục để cải thiện sức khỏe.";
        }
        exerciseTextView.setText(exerciseSuggestion);
    }
}
