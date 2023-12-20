package com.example.appapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import WebServices.Asynchtask;


public class MainActivity
        extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loginAPI(View v){
        String usuario = ((EditText)findViewById(R.id.txtusuario)).getText().toString();
        String clave = ((EditText)findViewById(R.id.txtclave)).getText().toString();

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebServices.WebService ws= new WebServices.WebService(
                /*"https://revistas.uteq.edu.ec/ws/login.php?usr=cristian&pass=123",*/

                "https://revistas.uteq.edu.ec/ws/login.php?usr="+ usuario + "&pass="+clave,
                datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView respuestaAPI = (TextView) findViewById(R.id.txtAPI);
        respuestaAPI.setText(result);
        Log.d("","API: "+result);

        if(result.equals("Login Correcto!")) {

            Intent x = new Intent(MainActivity.this, MainActivityBanco.class);
            startActivity(x);
        }
        else{
            respuestaAPI.setText("RESPUESTA DE API: "+result);
        }
    }
}