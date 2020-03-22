package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TelaDesafio extends AppCompatActivity implements SensorEventListener {
    public Aluno aluno = new Aluno();
    public TextView conta, resultado;
    public int desafio = 0;
    public int acertouSoma = 0, totalSoma = 0;
    public float atualX = (float) 0.0, atualY = (float) 0.0;
    public List<Float> listaX = new ArrayList<>();
    public List<Float> listaY = new ArrayList<>();
    public int stepX = 0, stepsX = 0;

    private SensorManager sensorManager;
    private Sensor sensor;

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
            aluno.setNomeAluno(extras.getString("nomeAluno"));//inserindo dados
            desafio = extras.getInt("desafio");

        }

        if (desafio == 1) {
            geraSoma();
        }

        //capturando resultado com movimentos
        sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

    }

    //recebendo resultados com base no sensor de movimentos
    public void onSensorChanged(SensorEvent event){
        synchronized (this){
            float x = event.values[0];
           //float y = event.values[1];

            //enviando dados atualizados para tela
            //resultado.setText(String.valueOf(x));

            atualX = (float) x * x;
            listaX.add(atualX);

            //tratando os valores do eixo x
            if (listaX.size() > 40) {
                listaX.remove(0);
                for (Float f : listaX) {
                    if (Math.abs(atualX - f) > 9.0) {
                        stepX++;
                    }
                }
                if (stepX > 10) {
                    stepsX++;
                    listaX = new ArrayList<>();
                    //vibrar();// vibrar
                }
                stepX = 0;
            }
            resultado.setText(String.valueOf(stepsX));

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

    public void geraSoma(){
        int valor1 = 0, valor2 = 0;
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
