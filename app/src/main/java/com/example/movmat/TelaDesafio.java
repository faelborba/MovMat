package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TelaDesafio extends AppCompatActivity implements SensorEventListener {
    public Aluno aluno = new Aluno();
    public TextView conta, resultado;
    public int desafio = 0;
    public float atualX = (float) 0.0, atualY = (float) 0.0;
    public List<Float> listaX = new ArrayList<>();
    public List<Float> listaY = new ArrayList<>();
    public int stepX = 0, stepsX = 0, stepY = 0, stepsY = 0;

    private SensorManager sensorManager;
    private Sensor sensor;
    private int resultadoInformado = 0, resultadoCerto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio);

        //capturando itens da tela
        conta = (TextView) findViewById(R.id.conta);
        resultado = (TextView) findViewById(R.id.resultado);

        //recebendo dados
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            /*aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            aluno.setComVideo(extras.getBoolean("comVideo"));
            aluno.setComSom(extras.getBoolean("comSom"));*/
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = extras.getInt("desafio");
            resultadoInformado = extras.getInt("resultadoInformado");
        }

        if (desafio == 1) {
            resultadoCerto = geraSoma();
        }

        //capturando resultado com movimentos
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

        resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDesafio.this, ConfereResultado.class);
                intent.putExtra("resultadoCerto", resultadoCerto);
                intent.putExtra("resultadoInformado", resultadoInformado);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                /*
                intent.putExtra("nomeAluno", aluno.getNomeAluno());
                intent.putExtra("comVideo", aluno.isComVideo());
                intent.putExtra("comSom", aluno.isComSom());*/
                startActivity(intent);
            }
        });
    }

    public int geraSoma() {
        int valor1 = 0, valor2 = 0;
        boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(5);

        for (int i = 0; continua; i++) {
            valor2 = random.nextInt(5);
            if ((valor2 + valor1) <= 10) {
                continua = false;
            }
        }
        conta.setText(valor1 + " + " + valor2 + " ?");
        Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();
        return (valor1 + valor2);
    }

    //recebendo resultados com base no sensor de movimentos
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            float x = event.values[0];
            float y = event.values[1];

            atualX = (float) x * x;
            atualY = (float) y * y;
            listaX.add(atualX);
            listaY.add(atualY);

            //tratando os valores do eixo x -- Dezenas
            if (listaX.size() > 40) {
                listaX.remove(0);
                for (Float f : listaX) {
                    if (Math.abs(atualX - f) > 9.0) {
                        stepX++;
                    }
                }
                if (stepX > 9) {
                    stepsX = stepsX + 10;
                    listaX = new ArrayList<>();
                    vibrar();// vibrar
                }
                stepX = 0;
            }
            resultado.setText(String.valueOf(stepsX + stepsY));

            //tratando os valores do eixo y -- unidades
            if (listaY.size() > 40) {
                listaY.remove(0);
                for (Float f : listaY) {
                    if (Math.abs(atualY - f) > 9.0) {
                        stepY++;
                    }
                }
                if (stepY > 9) {
                    stepsY++;
                    listaY = new ArrayList<>();
                    vibrar();
                }
                stepY = 0;
            }
            resultado.setText(String.valueOf(stepsX + stepsY));
            resultadoInformado = stepsX + stepsY;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void vibrar()// cotrole de vibração
    {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 30;//'30' é o tempo em milissegundos,duração da vibração.
        rr.vibrate(milliseconds);
    }
}
