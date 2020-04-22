package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class SelecionaDesafio extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public Desafio desafio = new Desafio();
    public TextView botaoSoma, botaoSubtrai, botaoMultiplica, botaoDivide, botaoAleatorio, botaoContar;

    @Override
    public void onBackPressed() {
        // não chame o super desse método
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_desafio);

        //pegando botões
        botaoSoma = (TextView) findViewById(R.id.botaoSoma);
        botaoSubtrai = (TextView) findViewById(R.id.botaoSubtrai);
        botaoMultiplica = (TextView) findViewById(R.id.botaoMultiplica);
        botaoDivide = (TextView) findViewById(R.id.botaoDvide);
        botaoAleatorio = (TextView) findViewById(R.id.botaoAleatorio);
        botaoContar = (TextView) findViewById(R.id.botaoContar);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            //Toast.makeText(SelecionaDesafio.this, "" + aluno.getNomeAluno()+ aluno.isComVideo() + aluno.isComSom(), Toast.LENGTH_SHORT).show();
        }
        //enviando dados
        botaoSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(1);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoSubtrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(2);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(3);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(4);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoAleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(5);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoContar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setDesafio(6);
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
