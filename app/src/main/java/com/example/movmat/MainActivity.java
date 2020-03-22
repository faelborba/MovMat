package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText digiteNome;
    private Button botaoOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Aluno aluno = new Aluno();

        digiteNome = (EditText) findViewById(R.id.digiteNome);

        //recebendo dados e executando a próxima tela
        botaoOk =  (Button) findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setNomeAluno(digiteNome.getText().toString());
                Intent intent = new Intent(MainActivity.this, ComVisualizazacao.class);
                intent.putExtra("nomeAluno", aluno.getNomeAluno());
                startActivity(intent);
            }
        });
    }
}