package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText digiteNome;
    private Button botaoOk;

    public Aluno aluno, alunoNovo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digiteNome = (EditText) findViewById(R.id.digiteNome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            alunoNovo = (Aluno) getIntent().getSerializableExtra("aluno");
            aluno = new Aluno();
            aluno.setNomeAluno(alunoNovo.getNomeAluno());
            //Toast.makeText(SelecionaDesafio.this, "" + aluno.getNomeAluno()+ aluno.isComVideo() + aluno.isComSom(), Toast.LENGTH_SHORT).show();
        }

        if (aluno == null) {
            aluno = new Aluno();
        } else {
            digiteNome.setText(aluno.getNomeAluno());
        }
        //recebendo dados e executando a próxima tela
        botaoOk = (Button) findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setNomeAluno(digiteNome.getText().toString());

                Intent intent = new Intent(MainActivity.this, ComVisualizazacao.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }
}
