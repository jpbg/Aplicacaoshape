package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    Button btnEntrar;
    UserDAO uDao;

    TextView tv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnCad);
        tv = findViewById(R.id.tvCadastrar);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Utilizando preferências para armazenar informações rápidas e que podem ser 'perdidas'.
                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email",edtEmail.getText().toString());
                editor.commit();

                uDao = new UserDAO(getApplicationContext(),
                        new User(edtEmail.getText().toString(),
                                edtSenha.getText().toString()));

                if (uDao.verificarEmailESenha()){
                    Intent it = new Intent(MainActivity.this,
                            ActivityPrincipal.class);
                    // it.putExtra("email", edtEmail.getText().toString());
                    startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this,
                            "Dados Incorretos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}