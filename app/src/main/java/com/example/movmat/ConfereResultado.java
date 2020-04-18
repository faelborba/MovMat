package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfereResultado extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public Desafio desafio = new Desafio();

    public int resultadoInformado = 0, resultadoCerto = 0;

    private TextView textoTela;
    private TextView botaoNao, botaoSim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confere_resultado);

        textoTela = (TextView) findViewById(R.id.txtResultado);
        botaoNao = (TextView) findViewById(R.id.botaoNao);
        botaoSim = (TextView) findViewById(R.id.botaoSim);

        //recebendo dados
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
        }
        if(desafio.getResultadoInformado() == desafio.getResultadoCerto()){// acertou
            textoTela.setText("Parabéns você acertou!\nDeseja continuar no desafio?");
            // adicionando os resultados aos valores conforme desafio.
            switch(desafio.getDesafio()){
                case 1:
                    aluno.setTotalSoma(1);
                    aluno.setVitoriasSoma(1);
                    break;
                case 2:
                    aluno.setTotalSubtracao(1);
                    aluno.setVitoriasSubtracao(1);
                    break;
                case 3:
                    aluno.setTotalMultiplicacao(1);
                    aluno.setVitoriasMultiplicacao(1);
                    break;
                case 4:
                    aluno.setTotalDivisao(1);
                    aluno.setVitoriasDivisao(1);
                    break;
                case 6:
                    aluno.setTotalContagem(1);
                    aluno.setVitoriaContagem(1);
                    break;
                default :
                    break;
            }
        }else{//errou
            textoTela.setText("Você errou!\nDeseja continuar no desafio?");
            switch(desafio.getDesafio()){
                case 1:
                    aluno.setTotalSoma(1);
                    break;
                case 2:
                    aluno.setTotalSubtracao(1);
                    break;
                case 3:
                    aluno.setTotalMultiplicacao(1);
                    break;
                case 4:
                    aluno.setTotalDivisao(1);
                    break;
                case 6:
                    aluno.setTotalContagem(1);
                    break;
                default :
                    break;
            }
        }
        desafio.setResultadoInformado(0);
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
