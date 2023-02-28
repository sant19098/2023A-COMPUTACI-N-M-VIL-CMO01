package com.example.myapplication222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
 import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    var boton :button  ?=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.boton)
        boton !!.setOnClickListener {
            Toast.makeText( context: this,text:  "mensaje", Toast.LENGTH_LONG).show()}

    }
}