package vistula.xy.l09_guda_66443_calculator_n10k;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



import android.content.Intent;
public class MainActivity extends AppCompatActivity {


    private EditText inputData;
    private Button sendToMemory, readFrmMemory;
    private TextView textViewIn, readViewOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData = findViewById(R.id.edit_write_to_file_id);
        sendToMemory = findViewById(R.id.to_file_btn_id);
        readViewOut = findViewById(R.id.from_file_vw_id);
        readFrmMemory = findViewById(R.id.from_file_btn_id);

        sendToMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataInput = inputData.getText().toString();

                Intent intent = new Intent(MainActivity.this, Calculator_n10.class);
                //intent.putExtras(bundle);
                startActivity(intent);
                writeToFile(dataInput);
            }

        });

        readFrmMemory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                readFromFile();
            }
        });


    }


    private void writeToFile(String data) {
        try {
            FileOutputStream fos = openFileOutput("user_Data.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.flush();
            osw.close();
            //fos.close();
            Toast.makeText(this, "Data written to file successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


        }
    }

    private void readFromFile(){
        try {
            // Create a FileInputStream to read from the file
            FileInputStream fis = openFileInput("user_Data.txt");

            //Create an InputStreamReader to handle character encoding
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }

            fis.close();

            readViewOut.setText("Data read from file:\n" + stringBuilder.toString());

            Toast.makeText(this, "Data read from file successfully", Toast.LENGTH_SHORT).show();


        } catch (IOException e){
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}