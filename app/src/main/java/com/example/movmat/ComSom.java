package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ComSom extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    private TextView botaoSim;
    private TextView botaoNao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_som);

        //pegando os botoes
        botaoSim = (TextView) findViewById(R.id.botaoSim2);
        botaoNao = (TextView) findViewById(R.id.botaoNao2);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            Toast.makeText(ComSom.this, ""+ aluno.getNomeAluno() + aluno.isComVideo(), Toast.LENGTH_SHORT).show();
        }
        botaoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setComSom(true);
                Intent intent = new Intent(ComSom.this, SelecionaDesafio.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setComSom(false);
                Intent intent = new Intent(ComSom.this, SelecionaDesafio.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
