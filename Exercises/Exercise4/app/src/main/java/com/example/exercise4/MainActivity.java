package com.example.exercise4;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button readButton, writeButton;
    private static final String FILE_NAME = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        readButton = findViewById(R.id.readButton);
        writeButton = findViewById(R.id.writeButton);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData();
            }
        });
    }

    private void readData() {
        File file = new File(getExternalFilesDir(null), FILE_NAME);
        StringBuilder text = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append('\n');
            }
            br.close();
            editText.setText(text.toString());
            Toast.makeText(getApplicationContext(), "File read successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void writeData() {
        File file = new File(getExternalFilesDir(null), FILE_NAME);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(editText.getText().toString().getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), "File saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error writing to file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
