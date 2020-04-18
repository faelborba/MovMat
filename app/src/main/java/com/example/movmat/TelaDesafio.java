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
    public Desafio desafio = new Desafio();
    public TextView conta, resultado;

    public float atualX = (float) 0.0, atualY = (float) 0.0;
    public List<Float> listaX = new ArrayList<>();
    public List<Float> listaY = new ArrayList<>();
    public int stepX = 0, stepsX = 0, stepY = 0, stepsY = 0;

    public int semente = 101;

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
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
        }

        if (desafio.getDesafio() == 1) {
            resultadoCerto = geraSoma();
        } else if (desafio.getDesafio() == 2) {
            resultadoCerto = geraSubtracao();
        } else if (desafio.getDesafio() == 3) {
            resultadoCerto = geraMultiplicacao();
        } else if (desafio.getDesafio() == 4) {
            resultadoCerto = geraDivisao();
        } else if (desafio.getDesafio() == 5) {
            resultadoCerto = geraAleatorio();
        } else if (desafio.getDesafio() == 6) {
            resultadoCerto = geraContagem();
        }

        //capturando resultado com movimentos
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

        resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafio.setResultadoCerto(resultadoCerto);
                desafio.setResultadoInformado(resultadoInformado);
                Intent intent = new Intent(TelaDesafio.this, ConfereResultado.class);
                intent.putExtra("desafio", desafio);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }

    public int geraContagem(){
        int valor = 0;
        Random random = new Random();
        valor = random.nextInt(semente);
        conta.setText("Desafio\nconte até\n" + valor);
        return valor;
    }

    public int geraAleatorio() {
        int sorteiaDesafio = 0;
        Random random = new Random();

        sorteiaDesafio = (random.nextInt(4) + 1);
        if (sorteiaDesafio == 1) {
            resultadoCerto = geraSoma();
        } else if (sorteiaDesafio == 2) {
            resultadoCerto = geraSubtracao();
        } else if (sorteiaDesafio == 3) {
            resultadoCerto = geraMultiplicacao();
        } else if (sorteiaDesafio == 4) {
            resultadoCerto = geraDivisao();
        }

        return resultadoCerto;
    }

    public int geraDivisao() {
        int valor1 = 0, valor2 = 0;
        //boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(semente);
        for (int i = 0; true; i++) {
            valor2 = random.nextInt(valor1 + 1);
            if (valor2 == 0) continue;
            if (valor1 % valor2 == 0) break;
        }
        String palavra = "Desafio\nquanto é\n" + valor1 + " ÷ " + valor2 + " ?";
        conta.setText(palavra);
        return (valor1 / valor2);
    }

    public int geraMultiplicacao() {
        int valor1 = 0, valor2 = 0;
        //boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(semente);

        for (int i = 0; true; i++) {
            valor2 = random.nextInt(semente);
            if ((valor2 * valor1) <= 100) {
                break;
            }
        }
        String palavra = "Desafio\nquanto é\n" + valor1 + " × " + valor2 + " ?";
        conta.setText(palavra);

        //Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();
        return (valor1 * valor2);
    }

    public int geraSubtracao() {
        int valor1 = 0, valor2 = 0;
        //boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(semente);
        valor2 = random.nextInt(valor1);

        String palavra = "Desafio\nquanto é\n" + valor1 + " - " + valor2 + " ?";
        conta.setText(palavra);

        //Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();
        return (valor1 - valor2);
    }

    public int geraSoma() {
        int valor1 = 0, valor2 = 0;
        //boolean continua = true;
        Random random = new Random();
        valor1 = random.nextInt(semente);

        for (int i = 0; true; i++) {
            valor2 = random.nextInt(semente);
            if ((valor2 + valor1) <= 100) {
                break;
            }
        }
        String palavra = "Desafio\nquanto é\n" + valor1 + " + " + valor2 + " ?";
        conta.setText(palavra);

        //Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();
        return (valor1 + valor2);
    }

    //recebendo resultados com base no sensor de movimentos
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            float x = event.values[0];
            float y = event.values[1];

            atualX = (float) x;
            atualY = (float) y;
            listaX.add(atualX);
            listaY.add(atualY);

            //tratando os valores do eixo x -- Dezenas
            /*if (atualX > 1) {
                stepX++;
            }
            if (stepX >10){
                stepsX = stepsX +10;
                vibrar();// vibrar
                stepX = 0;
            }

            if (atualY > 1) {
                stepY++;
            }
            if (stepY >10){
                stepsY++;
                vibrar();// vibrar
                stepY = 0;
            }*/

            //tratando os valores do eixo x -- Dezenas
            if (listaX.size() > 40) {
                listaX.remove(0);
                for (Float f : listaX) {
                    if (Math.abs(atualX - f) > 9.0) {
                        stepX++;
                    }
                }
                if (stepX > 5) {
                    stepsX = stepsX + 10; // contando eixo -- dezenas
                    listaX = new ArrayList<>();
                    vibrar();// vibrar
                }
                stepX = 0;
                stepY = 0;// zerando o step y para evitar problema de movimento errado
            }
            //tratando os valores do eixo y -- unidades
            if (listaY.size() > 40) {
                listaY.remove(0);
                for (Float f : listaY) {
                    if (Math.abs(atualY - f) > 9.0) {
                        stepY++;
                    }
                }
                if (stepY > 5) {
                    stepsY++;// contando eixo -- unidade
                    listaY = new ArrayList<>();
                    vibrar();
                }
                stepY = 0;
                stepX = 0;// zerando o step x para evitar problema de movimento errado
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
        long milliseconds = 60;//'30' é o tempo em milissegundos,duração da vibração.
        rr.vibrate(milliseconds);
    }
}
