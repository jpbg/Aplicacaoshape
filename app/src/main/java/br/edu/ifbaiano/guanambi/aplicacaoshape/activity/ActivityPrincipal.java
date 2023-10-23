package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;

public class ActivityPrincipal extends AppCompatActivity {

    TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtEmail = findViewById(R.id.txtEmail);

        SharedPreferences sp = getSharedPreferences("appLogin",
                Context.MODE_PRIVATE);
        String email = sp.getString("email","abc");

        txtEmail.setText(email);


       // Intent it = getIntent();
       // String email = it.getStringExtra("email");

        //txtEmail.setText(email);

    }
}