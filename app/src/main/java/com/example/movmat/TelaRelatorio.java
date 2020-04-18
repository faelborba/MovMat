package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaRelatorio extends AppCompatActivity {
    private TextView botaoOk, somaAcertou, somaErrou, somaTotal;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            Toast.makeText(this, "" + aluno.getNomeAluno()+ " " +aluno.isComVideo() + aluno.isComSom() , Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Vitoria Soma " + aluno.vitoriasSoma+ "\n Total de desafios soma" + aluno.getTotalSoma() + "\n Erros Soma" + (aluno.getTotalSoma()- aluno.getVitoriasSoma()), Toast.LENGTH_SHORT).show();
        }

        somaAcertou.setText("" + aluno.getVitoriasSoma());
        somaErrou.setText("" + (aluno.getTotalSoma() - aluno.getVitoriasSoma()));
        somaTotal.setText("" + aluno.getTotalSoma());

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
