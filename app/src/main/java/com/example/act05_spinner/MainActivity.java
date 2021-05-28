package com.example.act05_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText cajaGrado, cajaResultado;
    Spinner spinnerConversor1, spinnerConversor2;
    int posicion1 = 1;
    int posicion2 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaGrado = findViewById(R.id.cajaGrado);
        cajaResultado = findViewById(R.id.cajaResultado);

        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.spinner_conversion, android.R.layout.simple_spinner_item);

        spinnerConversor1 = findViewById(R.id.spinnerConv1);
        spinnerConversor1.setAdapter(adaptador);
        spinnerConversor1.setOnItemSelectedListener(this);

        spinnerConversor2 = findViewById(R.id.spinnerConv2);
        spinnerConversor2.setAdapter(adaptador);
        spinnerConversor2.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String texto = cajaGrado.getText().toString();
        if (texto.equals("")){
            texto = "0";
        }
        double gradoIng = Double.parseDouble(texto);
        double resultadoConv = gradoIng;

        if(parent.getId() == R.id.spinnerConv1){
            posicion1 = position;
        }
        if (parent.getId() == R.id.spinnerConv2){
            posicion2 = position;
        }

        switch (spinnerConversor1.getItemAtPosition(posicion1).toString()){
            case "Centigrados":
                switch (spinnerConversor2.getItemAtPosition(posicion2).toString()){
                    case "Fahrenheit": resultadoConv = (gradoIng * 1.8) + 32;
                    break;
                    case "Rankine": resultadoConv = (gradoIng * 1.8) + 32 + 459.67;
                    break;
                    case "Kelvin": resultadoConv = gradoIng + 273.15;
                    break;
                    default:
                        break;
                }
                break;
            case "Fahrenheit":
                switch (spinnerConversor2.getItemAtPosition(posicion2).toString()){
                    case "Centigrados": resultadoConv = (gradoIng - 32) / 1.8;
                    break;
                    case "Rankine": resultadoConv = gradoIng + 459.67;
                    break;
                    case "Kelvin": resultadoConv = (gradoIng + 459.67)/ 1.8;
                    break;
                    default:
                        break;
                }
                break;
            case "Rankine":
                switch (spinnerConversor2.getItemAtPosition(posicion2).toString()){
                    case "Centigrados": resultadoConv = (gradoIng - 491.67) / 1.8;
                    break;
                    case "Fahrenheit": resultadoConv = gradoIng - 459.67;
                    break;
                    case "Kelvin": resultadoConv = gradoIng / 1.8;
                    break;
                    default:
                        break;
                }
                break;
            case "Kelvin":
                switch (spinnerConversor2.getItemAtPosition(posicion2).toString()){
                    case "Centigrados": resultadoConv = gradoIng - 273.15;
                    break;
                    case "Fahrenheit": resultadoConv = (gradoIng * 1.8) - 459.67;
                    break;
                    case "Rankine": resultadoConv = gradoIng * 1.8;
                    break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        cajaResultado.setText("" + resultadoConv);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}