package com.example.hospital.internalStorgae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class filedataActivity extends AppCompatActivity {
    private static final String TAG = "TalibfiledataActivity";
    private static final String FILE_NAME = "record.txt";
    TextView textView ;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    String str;
    File file;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: filedataActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedata);
        Log.d(TAG, "onCreate: ");
        textView = findViewById(R.id.textView1);

        Intent intent = getIntent();
        str = intent.getStringExtra("summary");
        str += "\n\n\n\n";

        createFie();
        load();
    }
    public void createFie() {
        Log.d(TAG, "createFie: inside method");
        if (file == null){
            file = new File(FILE_NAME);
        }
        try {
            fos = openFileOutput(FILE_NAME,MODE_APPEND);
            fos.write(str.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            textView.setText(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
