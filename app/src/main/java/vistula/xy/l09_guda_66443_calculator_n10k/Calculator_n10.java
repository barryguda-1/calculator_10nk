package vistula.xy.l09_guda_66443_calculator_n10k;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator_n10 extends AppCompatActivity {

    public EditText editText1, editText2;
    public TextView resultVw, readFrmFile;
    public Button btnCalculate;

    public Spinner spinnerItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_n10);

        editText1 = findViewById(R.id.editText_1_id);
        editText2 = findViewById(R.id.editText_2_id);
        resultVw = findViewById(R.id.results_Vw);
        spinnerItems = findViewById(R.id.spinner_num);
        btnCalculate = findViewById(R.id.calculate_btn);
        readFrmFile = findViewById(R.id.read_from_vw_id);

        readFromFile();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
                //readFromFile();
            }
        });


    }

    public void calculateResult() {
        String numberStr1 = editText1.getText().toString();
        String numberStr2 = editText2.getText().toString();
        int base = getSelectedBase();
        try {
            int num1 = Integer.parseInt(numberStr1, base);
            int num2 = Integer.parseInt(numberStr2, base);
            int result = num1 + num2;

            String resultStr = Integer.toString(result, base);
            resultVw.setText(resultStr);
        } catch (NumberFormatException e) {
            resultVw.setText("Invalid input. Please enter valid numbers.");
        }

    }

    private int getSelectedBase() {
        String selectedBase = spinnerItems.getSelectedItem().toString();
        switch (selectedBase) {
            case "2":
                return 2;
            case "8":
                return 8;
            case "16":
                return 16;
            default:
                return 10;

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

            readFrmFile.setText("Data read from file:\n" + stringBuilder.toString());

            Toast.makeText(this, "Data read from file successfully", Toast.LENGTH_SHORT).show();


        } catch (IOException e){
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}