package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComVisualizazacao extends AppCompatActivity {
    public Aluno aluno = new Aluno();
    private TextView botaoSim;
    private TextView botaoNao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_visualizazacao);

        //pegando os botoes
        botaoSim = (TextView) findViewById(R.id.botaoSim);
        botaoNao = (TextView) findViewById(R.id.botaoNao);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            Toast.makeText(ComVisualizazacao.this, ""+ aluno.getNomeAluno(), Toast.LENGTH_SHORT).show();
        }
        botaoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComVisualizazacao.this, ComSom.class);
                intent.putExtra("nomeAluno", aluno.getNomeAluno());
                startActivity(intent);
            }
        });
        botaoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComVisualizazacao.this, ComSom.class);
                intent.putExtra("nomeAluno", aluno.getNomeAluno());
                startActivity(intent);
            }
        });
    }
}
