package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SelecionaDesafio extends AppCompatActivity {
    public Aluno aluno = new Aluno();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_desafio);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            Toast.makeText(SelecionaDesafio.this, ""+ aluno.getNomeAluno(), Toast.LENGTH_SHORT).show();
        }
    }
}
