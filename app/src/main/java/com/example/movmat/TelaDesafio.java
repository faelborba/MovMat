package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

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

    ArrayList<MediaPlayer> mediaPlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio);

        alimentaSom();
        //capturando itens da tela
        conta = (TextView) findViewById(R.id.conta);
        resultado = (TextView) findViewById(R.id.resultado);

        //recebendo dados
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
        }

        if (!aluno.isComVideo()) {
            resultado.setTextColor(Color.BLACK);
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

    public int geraContagem() {
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
        valor1 = random.nextInt(11);

        for (int i = 0; true; i++) {
            valor2 = random.nextInt(11);
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

                    resultado.setText(String.valueOf(stepsX + stepsY));
                    resultadoInformado = stepsX + stepsY;

                    if (aluno.isComSom()) tocaSom(resultadoInformado);
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

                    resultado.setText(String.valueOf(stepsX + stepsY));
                    resultadoInformado = stepsX + stepsY;

                    if (aluno.isComSom()) tocaSom(resultadoInformado);
                }
                stepY = 0;
                stepX = 0;// zerando o step x para evitar problema de movimento errado
            }

        }
    }

    protected void alimentaSom() {
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.zero));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.um));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dois));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.tres));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.quatro));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.cinco));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.seis));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.sete));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.oito));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.nove));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dez));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.onze));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.doze));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.treze));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.quatorze));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.quinze));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dezeseis));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dezesete));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dezoito));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.dezenove));
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.vinte));// 20
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.trinta));//21
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.quarenta));//22
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.cinquenta));//23
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.sessenta));//24
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.setenta));//25
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.oitenta));//26
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.noventa));//27
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.cem));//28
        mediaPlayer.add(MediaPlayer.create(TelaDesafio.this, R.raw.eee));//29

    }

    protected void tocaSom(final int resultado) {
        new Thread() {
            public void run() {
                if (resultado < 20) {
                    mediaPlayer.get(resultado).start();
                } else if (resultado < 30) {
                    mediaPlayer.get(20).start();
                    if (resultado != 20) {
                        while (mediaPlayer.get(20).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 20).start();
                    }
                } else if (resultado < 40) {
                    mediaPlayer.get(21).start();
                    if (resultado != 30) {
                        while (mediaPlayer.get(21).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 30).start();
                    }
                } else if (resultado < 50) {
                    mediaPlayer.get(22).start();
                    if (resultado != 40) {
                        while (mediaPlayer.get(22).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 40).start();
                    }
                } else if (resultado < 60) {
                    mediaPlayer.get(23).start();
                    if (resultado != 50) {
                        while (mediaPlayer.get(23).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 50).start();
                    }
                } else if (resultado < 70) {
                    mediaPlayer.get(24).start();
                    if (resultado != 60) {
                        while (mediaPlayer.get(24).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 60).start();
                    }
                } else if (resultado < 80) {
                    mediaPlayer.get(25).start();
                    if (resultado != 70) {
                        while (mediaPlayer.get(25).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 70).start();
                    }
                } else if (resultado < 90) {
                    mediaPlayer.get(26).start();
                    if (resultado != 80) {
                        while (mediaPlayer.get(26).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 80).start();
                    }
                } else if (resultado < 100) {
                    mediaPlayer.get(27).start();
                    if (resultado != 90) {
                        while (mediaPlayer.get(27).isPlaying()) ;// loop para segurar o som
                        mediaPlayer.get(29).start();
                        while (mediaPlayer.get(29).isPlaying()) ;
                        mediaPlayer.get(resultado - 90).start();
                    }
                } else if (resultado == 100) {
                    mediaPlayer.get(28).start();
                }
            }
        }.start();

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
