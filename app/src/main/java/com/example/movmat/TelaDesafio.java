package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class TelaDesafio extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    public TextView conta;
    public int desafio = 0;

    public int acertouSoma = 0, totalSoma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio);

        conta = (TextView) findViewById(R.id.conta);

        //recebendo dados
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            desafio = extras.getInt("desafio");

        }

        if (desafio == 1) {
            geraSoma();
        }

        //esperando resultado com movimentos

    }

    public void geraSoma(){
        int valor1 = 0, valor2 = 0, somaTeste = 100, somaFinal = 0;
        boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(100);

        for(int i =0; continua; i++){
            valor2 = random.nextInt(100);
            if((valor2 + valor1) <= 100){
                continua = false;
            }
        }
        conta.setText(valor1 + " + " + valor2);
        Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();

    }
}
