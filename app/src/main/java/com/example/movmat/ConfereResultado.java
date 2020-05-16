package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfereResultado extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public Desafio desafio = new Desafio();

    private TextView textoTela;
    private TextView botaoNao, botaoSim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confere_resultado);

        textoTela = (TextView) findViewById(R.id.txtResultado);
        botaoNao = (TextView) findViewById(R.id.botaoNao);
        botaoSim = (TextView) findViewById(R.id.botaoOk);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
        }
        if (desafio.getResultadoInformado() == desafio.getResultadoCerto()) {// acertou
            textoTela.setText("Parabéns " + aluno.getNomeAluno() + " você acertou!\nDeseja continuar nessa atividade?");
            // adicionando os resultados aos valores conforme desafio.
            if (desafio.getDesafio() == 1 || desafio.getSorteiaDesafio() == 1) {
                aluno.setTotalSoma(1);
                aluno.setVitoriasSoma(1);
            } else if (desafio.getDesafio() == 2 || desafio.getSorteiaDesafio() == 2) {
                aluno.setTotalSubtracao(1);
                aluno.setVitoriasSubtracao(1);
            } else if (desafio.getDesafio() == 3 || desafio.getSorteiaDesafio() == 3) {
                aluno.setTotalMultiplicacao(1);
                aluno.setVitoriasMultiplicacao(1);
            } else if (desafio.getDesafio() == 4 || desafio.getSorteiaDesafio() == 4) {
                aluno.setTotalDivisao(1);
                aluno.setVitoriasDivisao(1);
            } else if (desafio.getDesafio() == 5 || desafio.getSorteiaDesafio() == 5) {
                aluno.setTotalContagem(1);
                aluno.setVitoriaContagem(1);
            }
        } else {//errou
            textoTela.setText("Que pena " + aluno.getNomeAluno() + " Você errou!\nDeseja continuar nessa atividade?");
            if (desafio.getDesafio() == 1 || desafio.getSorteiaDesafio() == 1) {
                aluno.setTotalSoma(1);
            } else if (desafio.getDesafio() == 2 || desafio.getSorteiaDesafio() == 2) {
                aluno.setTotalSubtracao(1);
            } else if (desafio.getDesafio() == 3 || desafio.getSorteiaDesafio() == 3) {
                aluno.setTotalMultiplicacao(1);
            } else if (desafio.getDesafio() == 4 || desafio.getSorteiaDesafio() == 4) {
                aluno.setTotalDivisao(1);
            } else if (desafio.getDesafio() == 5 || desafio.getSorteiaDesafio() == 5) {
                aluno.setTotalContagem(1);
            }
        }
        desafio.setResultadoInformado(0);//zerando
        desafio.setSorteiaDesafio(0);//zerando
        botaoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfereResultado.this, TelaDesafio.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
        botaoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfereResultado.this, TelaRelatorio.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
