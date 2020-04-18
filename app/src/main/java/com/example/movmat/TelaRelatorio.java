package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TelaRelatorio extends AppCompatActivity {
    private TextView botaoOk;
    private TextView somaAcertou, somaErrou, somaTotal;
    private TextView subtraiAcertou, subtraiErrou, subtraiTotal;
    private TextView multiplicaAcertou, multiplicaErrou, multiplicaTotal;
    private TextView divideAcertou, divideErrou, divideTotal;
    private TextView contagemAcertou, contagemErrou, contagemTotal;

    public Aluno aluno = new Aluno();

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_relatorio);

        somaAcertou = (TextView) findViewById(R.id.somaAcertou);
        somaErrou = (TextView) findViewById(R.id.somaErrou);
        somaTotal = (TextView) findViewById(R.id.somaTotal);
        subtraiAcertou = (TextView) findViewById(R.id.subtraiAcertou);
        subtraiErrou = (TextView) findViewById(R.id.subtraiErrou);
        subtraiTotal = (TextView) findViewById(R.id.subtraiTotal);
        multiplicaAcertou = (TextView) findViewById(R.id.multiplicaAcertou);
        multiplicaErrou = (TextView) findViewById(R.id.multiplicaErrou);
        multiplicaTotal = (TextView) findViewById(R.id.multiplicaTotal);
        divideAcertou = (TextView) findViewById(R.id.divisaoAcertou);
        divideErrou = (TextView) findViewById(R.id.divisaoErrou);
        divideTotal = (TextView) findViewById(R.id.divisaoTotal);
        contagemAcertou = (TextView) findViewById(R.id.contagemAcertou);
        contagemErrou = (TextView) findViewById(R.id.contagemErrou);
        contagemTotal = (TextView) findViewById(R.id.contagemTotal);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            /*Toast.makeText(this, "" + aluno.getNomeAluno() + " " + aluno.isComVideo() + aluno.isComSom(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Vitoria Soma " + aluno.vitoriasSoma + "\n Total de desafios soma" + aluno.getTotalSoma() + "\n Erros Soma" + (aluno.getTotalSoma() - aluno.getVitoriasSoma()), Toast.LENGTH_SHORT).show();*/
        }

        somaAcertou.setText("" + aluno.getVitoriasSoma());
        somaErrou.setText("" + (aluno.getTotalSoma() - aluno.getVitoriasSoma()));
        somaTotal.setText("" + aluno.getTotalSoma());
        subtraiAcertou.setText("" + aluno.getVitoriasSubtracao());
        subtraiErrou.setText("" + (aluno.getTotalSubtracao() - aluno.getVitoriasSubtracao()));
        subtraiTotal.setText("" + aluno.getTotalSubtracao());
        multiplicaAcertou.setText("" + aluno.getVitoriasMultiplicacao());
        multiplicaErrou.setText("" + (aluno.getTotalMultiplicacao()-aluno.getVitoriasMultiplicacao()));
        multiplicaTotal.setText("" + aluno.getTotalMultiplicacao());
        divideAcertou.setText("" + aluno.getVitoriasDivisao());
        divideErrou.setText("" + (aluno.getTotalDivisao() - aluno.getVitoriasDivisao()));
        divideTotal.setText("" + aluno.getTotalDivisao());
        contagemAcertou.setText("" + aluno.getVitoriaContagem());
        contagemErrou.setText("" + (aluno.getTotalContagem() - aluno.getVitoriaContagem()));
        contagemTotal.setText("" + aluno.getTotalContagem());


        botaoOk = (TextView) findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaRelatorio.this, SelecionaDesafio.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
