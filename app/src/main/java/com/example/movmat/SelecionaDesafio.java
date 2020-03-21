package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SelecionaDesafio extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public TextView botaoSoma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_desafio);

        //pegando botões
        botaoSoma = (TextView) findViewById(R.id.botaoSoma);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            Toast.makeText(SelecionaDesafio.this, ""+ aluno.getNomeAluno(), Toast.LENGTH_SHORT).show();
        }
        //enviando dados
        botaoSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecionaDesafio.this, TelaDesafio.class);
                intent.putExtra("nomeAluno", aluno.getNomeAluno());
                intent.putExtra("desafio", 1);
                startActivity(intent);
            }
        });
    }
}
