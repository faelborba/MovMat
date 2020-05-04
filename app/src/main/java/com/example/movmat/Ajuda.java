package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ajuda extends AppCompatActivity {
    public String descricao;
    public TextView texto;
    public Aluno aluno = new Aluno();
    public Desafio desafio = new Desafio();
    public int tela = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        texto = (TextView) findViewById(R.id.textoPrincipal);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            descricao = (String) getIntent().getSerializableExtra("descricao");
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
            tela = (int) extras.getInt("tela");
        }

        texto.setText(String.valueOf(descricao));
    }

    public void onBackPressed() {
        Intent intent;
        // não chame o super desse método
        if(tela == 1){
            intent= new Intent(this, MainActivity.class);
        }else if (tela == 2){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("aluno", aluno);
        }else{
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("aluno", aluno);
        }

        startActivity(intent);
        super.onBackPressed();
    }
}
