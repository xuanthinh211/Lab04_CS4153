package com.example.exercise2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button readButton, writeButton;
    private static final String FILE_NAME = "myfile.xml";

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
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fis);
            Element root = doc.getDocumentElement();
            NodeList contentList = root.getElementsByTagName("content");
            if (contentList.getLength() > 0) {
                String content = contentList.item(0).getTextContent();
                editText.setText(content);
            } else {
                editText.setText("");
            }
            fis.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void writeData() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            String content = editText.getText().toString();
            String xmlContent = "<data><content>" + content + "</content></data>";
            fos.write(xmlContent.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), "File saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error writing to file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
