package com.example.reniecdataapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.reniecdataapp.Class.Ciudadano;
import com.example.reniecdataapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private EditText txtnumDoc;
    private Button btnConsultarDoc;
    private ImageView imgFoto;

    RequestQueue rq;
    JsonObjectRequest jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnumDoc = findViewById(R.id.txtdni);
        btnConsultarDoc = findViewById(R.id.btnConsultarDoc);
        imgFoto = findViewById(R.id.imgFoto);

        rq= Volley.newRequestQueue(this);

        btnConsultarDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarForm();
            }
        });


    }


    public void validarForm(){
       String dni;
       dni = txtnumDoc.getText().toString().trim();
        if(dni.length() <8)
            txtnumDoc.setError("Ingrese un DNI Válido!!");
        else {
           buscarResultados("http://172.18.98.47:7001/mavenAPI/api/getConsultaDNI/?dni=05244720");
        }

    }

    private void buscarResultados(final String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    jsonObject = jsonArray.getJSONObject(0);
                    String apPrimer = jsonObject.optString("apPrimer");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mensaje de error
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<String,String>();
                return parametros;


            }
        };
    }


}
