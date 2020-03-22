package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TelaRelatorio extends AppCompatActivity {
    private TextView botaoOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_relatorio);

        Bundle extras = getIntent().getExtras();

        botaoOk = (TextView) findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaRelatorio.this, SelecionaDesafio.class);

                startActivity(intent);
            }
        });
    }
}
