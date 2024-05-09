package com.example.exercise1;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class  EMainActivity extends AppCompatActivity {

    EditText editdata;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editdata = findViewById(R.id.editdata);
        btn = findViewById(R.id.btnreaddata);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Mở tệp trong thư mục res/drawable
                    InputStream in = getResources().openRawResource(R.raw.myfile);
                    InputStreamReader inreader = new InputStreamReader(in);
                    BufferedReader bufreader = new BufferedReader(inreader);
                    StringBuilder builder = new StringBuilder();
                    String data;

                    // Đọc dữ liệu từ tệp và append vào StringBuilder
                    while ((data = bufreader.readLine()) != null) {
                        builder.append(data).append("\n");
                    }

                    // Đóng luồng đọc
                    in.close();

                    // Hiển thị dữ liệu lên EditText
                    editdata.setText(builder.toString());
                } catch (IOException ex) {
                    // Xử lý ngoại lệ nếu có lỗi khi đọc tệp
                    ex.printStackTrace();
                }
            }
        });
    }
}