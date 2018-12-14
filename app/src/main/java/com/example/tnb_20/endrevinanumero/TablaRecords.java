package com.example.tnb_20.endrevinanumero;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class TablaRecords extends AppCompatActivity {

    public int intentos;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_records);

        // busquem la ListView i li endollem el ArrayAdapter
        TextView lv = findViewById(R.id.tabla);
        for (int i=0;i<MainActivity.records2.size();i++){
            lv.setText(lv.getText()+MainActivity.records2.get(i).name + "-" +MainActivity.records2.get(i).intentos+"\n");
        }
        }


    }
