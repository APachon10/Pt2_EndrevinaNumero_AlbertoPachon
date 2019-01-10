package com.example.tnb_20.endrevinanumero;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;


public class TablaRecords extends AppCompatActivity {

    public int intentos;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_records);

        TextView lv = (TextView) findViewById(R.id.tabla);
        for (int i=0;i<MainActivity.records2.size();i++){

            lv.setText(MainActivity.records2.get(i).name+"                                                         "+MainActivity.records2.get(i).intentos + "\n");
        }
    }
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
