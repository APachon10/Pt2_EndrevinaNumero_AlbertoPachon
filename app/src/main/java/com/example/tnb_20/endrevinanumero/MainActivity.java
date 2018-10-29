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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected int valorAleatorio;
    protected int intentos;
    protected String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentos = 0;
        /*Generamos Numero Aleatorio */
        Random r = new Random();
        valorAleatorio = r.nextInt(100);
        /**/
        /*Creamos e Iniciamos el Boton*/
        final Button enviar = findViewById(R.id.Enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            /*Programamos */
            @Override
            public void onClick(View v) {

                EditText et = findViewById(R.id.Num_Usuario);
                String s = et.getText().toString();
                int valor = Integer.parseInt(s);
                if(valorAleatorio == valor){
                    Context context = getApplicationContext();
                    CharSequence text = "You Win ";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    if(valorAleatorio > valor){
                        Context context = getApplicationContext();
                        CharSequence text = "El valor es Incorrecto,El Numero que buscas es mas Grande "+Integer.toString(valorAleatorio);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }else {
                        Context context = getApplicationContext();
                        CharSequence text = "El valor es Incorrecto,El Numero que buscas es mas Pequeño";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    intentos++;
                }
            }
        });

        final Button record = findViewById(R.id.Records);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Title");

                Button button = dialog.findViewById(R.id.button_ok);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        EditText edit=dialog.findViewById(R.id.user_name);
                        String text=edit.getText().toString();

                        dialog.dismiss();
                        name=text;
                    }
                });
                dialog.show();
            }
        });
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, TablaRecords.class);
        EditText editText = findViewById(R.id.Num_Usuario);
        String message = editText.getText().toString();
        intent.putExtra("MSG1", message);
        startActivity(intent);
    }
}
