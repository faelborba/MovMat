package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConfereResultado extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public int desafio = 0;
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
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            desafio = extras.getInt("desafio");
            resultadoInformado = extras.getInt("resultadoInformado");
            resultadoCerto = extras.getInt("resultadoCerto");
        }
        if(resultadoInformado == resultadoCerto){
            textoTela.setText("Parabéns você acertou!\nDeseja continuar no desafio?");
            Toast.makeText(ConfereResultado.this, "Acertou miseravi " + resultadoInformado, Toast.LENGTH_SHORT).show();
        }else{
            textoTela.setText("Você errou!\nDeseja continuar no desafio?");
            Toast.makeText(ConfereResultado.this, "Errou miseravi, informou " + resultadoInformado +" correto era: "+ resultadoCerto, Toast.LENGTH_SHORT).show();
        }

        botaoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfereResultado.this, TelaDesafio.class);
                intent.putExtra("resultadoInformado", 0);
                intent.putExtra("desafio", desafio);
                startActivity(intent);
            }
        });

        botaoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfereResultado.this, TelaRelatorio.class);

                startActivity(intent);
            }
        });
    }
}
