package com.example.tnb_20.endrevinanumero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class TablaRecords extends AppCompatActivity {

    public int intentos;
    public String name;

    class Record {
        public Record(int _intents, String _nom) {
            intentos = _intents;
            name = _nom;
        }
    }
    ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_records);

        // Inicialitzem model
        records = new ArrayList<Record>();
        
        // busquem la ListView i li endollem el ArrayAdapter
        TextView lv = (TextView) findViewById(R.id.tabla);
        lv.setText(name + " " +intentos);
        }


    }
