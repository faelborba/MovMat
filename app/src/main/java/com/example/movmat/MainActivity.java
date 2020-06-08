package com.example.movmat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText digiteNome;
    private Button botaoOk, botaoInstrucoes;

    public Aluno aluno, alunoNovo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pegando a variável da tela
        digiteNome = (EditText) findViewById(R.id.digiteNome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            alunoNovo = (Aluno) getIntent().getSerializableExtra("aluno");
            if (alunoNovo != null) {
                aluno = new Aluno();
                aluno.setNomeAluno(alunoNovo.getNomeAluno());
                Toast.makeText(MainActivity.this, "" + aluno.getNomeAluno(), Toast.LENGTH_SHORT).show();
            }
        }
        if (aluno == null) {
            aluno = new Aluno();
        } else {
            digiteNome.setText(aluno.getNomeAluno());
        }

        //marcando texto da caixa como selecionado para apagar rápido
        digiteNome.setSelectAllOnFocus(true);
        digiteNome.selectAll();

        botaoInstrucoes = (Button) findViewById(R.id.botaoInstrucoes);
        botaoInstrucoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ajuda.class);
                //intent.putExtra("descricao", descricao);
                intent.putExtra("tela", 1);
                startActivity(intent);
            }
        });

        //recebendo dados e executando a próxima tela
        botaoOk = (Button) findViewById(R.id.botaoOk);

        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setNomeAluno(digiteNome.getText().toString());
                if (!aluno.getNomeAluno().equals("Digite o nome")) {
                    Intent intent = new Intent(MainActivity.this, TelaConfiguracao.class);
                    intent.putExtra("aluno", aluno);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor digite um nome válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBackPressed() {// Sair da aplicação se clicar em voltar
        finishAffinity();
    }
}
