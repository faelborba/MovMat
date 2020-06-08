package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class TelaConfiguracao extends AppCompatActivity {
    public Aluno aluno;
    private Button botaoOk;
    private Button botaoIstrucao;
    private CheckBox comVisual, comSom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configuracao);

        //pegando os botoes
        comVisual = (CheckBox) findViewById(R.id.comVisual);
        comSom = (CheckBox) findViewById(R.id.comSom);
        botaoIstrucao = (Button) findViewById(R.id.botaoInstrucoes2);
        botaoOk = (Button) findViewById(R.id.botaoOk);

        comSom.setChecked(true);// setando o check
        comVisual.setChecked(true);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");// recebendo o objeto aluno
        }

        botaoIstrucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaConfiguracao.this, Ajuda.class);
                intent.putExtra("aluno", aluno);
                intent.putExtra("tela", 2);
                startActivity(intent);
            }
        });

        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setComVideo(comVisual.isChecked());
                aluno.setComSom(comSom.isChecked());
                Intent intent = new Intent(TelaConfiguracao.this, SelecionaDesafio.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
