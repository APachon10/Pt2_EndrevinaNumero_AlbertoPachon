package com.example.tnb_20.endrevinanumero;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected int valorAleatorio;
    protected int intentos;
    protected String name;

    static ArrayList<Record> records2 = new ArrayList<Record>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentos = 0;
        //Definimos el nombre del Fichero
        final String nombre_fichero = "App_Records_Persistence.txt";
        records2.clear();
        //Abrimos el Archivo
        records2 = leerFichero(nombre_fichero);
        /*Generamos Numero Aleatorio */
        Random r = new Random();
        generarNumeroAleatorio(r);

        /*Creamos e Iniciamos el Boton*/
        final Button enviar = findViewById(R.id.Enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            /*Programamos */
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.Num_Usuario);
                String s = et.getText().toString();
                    int valor = Integer.parseInt(s);
                    if (valorAleatorio == valor) {
                        NumeroAcertado();
                        intentos = intentos;

                        //Iniciamos el Dialogo
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Title");

                        final Button button = dialog.findViewById(R.id.button_ok);
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                //Insetamos el Nombre del Usuario
                                EditText edit = dialog.findViewById(R.id.user_name);
                                String text = edit.getText().toString();

                                dialog.dismiss();
                                name = text;

                                Intent intent = new Intent(MainActivity.this, TablaRecords.class);
                                EditText editText = dialog.findViewById(R.id.user_name);
                                String message = editText.getText().toString();
                                if (records2.size() <= 0) {
                                    //Añadimos los Datos nuevos al ArrayList
                                    records2.add(new Record(intentos, message));
                                    // Pasamos lso Datos al metodo para insertarlo dentro de un fichero que le indicamos
                                    guardarFichero(message, intentos, nombre_fichero);
                                } else if (records2.size() > 0) {
                                    records2.add(new Record(intentos, message));
                                    guardarFichero(message, intentos, nombre_fichero);
                                }
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    } else {
                        if (valorAleatorio > valor) {
                            numeroMasGrande();
                        } else {
                            numeroMasPeque();
                        }
                        intentos++;
                    }
            }
        });
        //Boton para Abrir la tabla de Records
        final Button record = findViewById(R.id.Records);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TablaRecords.class);
                startActivity(intent);
            }
        });
    }
    public int generarNumeroAleatorio(Random r ){
        valorAleatorio = r.nextInt(100);
        return valorAleatorio;
    }
    public void NumeroAcertado(){
        Context context = getApplicationContext();
        CharSequence text = "Felicidades , Has Ganado ";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void numeroMasGrande(){
        Context context = getApplicationContext();
        CharSequence text = "El valor es Incorrecto,El Numero que buscas es mas Grande ";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        EditText edit2=findViewById(R.id.Num_Usuario);
        edit2.setText("");
    }
    public void numeroMasPeque(){
        Context context = getApplicationContext();
        CharSequence text = "El valor es Incorrecto,El Numero que buscas es mas Pequeño";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        EditText edit2=findViewById(R.id.Num_Usuario);
        edit2.setText("");
    }
    public void guardarFichero(String nombre, int Intentos,String nombre_file){
        try {
            OutputStreamWriter fichero = new OutputStreamWriter(openFileOutput(nombre_file,Context.MODE_APPEND));

            fichero.write(nombre +","+intentos);
            fichero.write("\r\n");
            fichero.close();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Record> leerFichero(String nombre_file){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(nombre_file)));
            String linea;
            String texto [];
            while((linea = br.readLine())!=null){
                texto = linea.split(",");
                Record r = new Record(Integer.parseInt(texto[1]),texto[0]);
                records2.add(r);
            }
            br.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return records2;
    }
}