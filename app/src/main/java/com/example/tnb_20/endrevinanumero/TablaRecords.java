package com.example.tnb_20.endrevinanumero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;



public class TablaRecords extends AppCompatActivity {

    public int intentos;
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_records);
        final String nombre_fichero = "App_Records_Persistence.txt";
        MainActivity m = new MainActivity();
        MainActivity.records2 = m.leerFichero(nombre_fichero);

        ArrayAdapter<Record> Adaptador = new ArrayAdapter<Record>(this, R.layout.item_layout, MainActivity.records2){
            @Override
            public View getView(int indice, View convertView, ViewGroup container)
            {
                if( convertView==null ) {
                    convertView = getLayoutInflater().inflate(R.layout.item_layout, container, false);
                }
                for (int i=0;i<MainActivity.records2.size();i++){
                    name = MainActivity.records2.get(i).name;
                    intentos = MainActivity.records2.get(i).intentos;
                    ((TextView) convertView.findViewById(R.id.User)).setText(getItem(indice).name);
                    ((TextView) convertView.findViewById(R.id.Rec)).setText(Integer.toString(getItem(indice).intentos));
                }
                return convertView;
            }
        };
        ListView lv = (ListView) findViewById(R.id.Lista);
        lv.setAdapter(Adaptador);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}