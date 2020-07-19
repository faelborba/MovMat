/*classe responsável por gerar as atividades e capturar os movimentos do giroscópio.*/
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
import android.view.WindowManager;
import android.widget.Button;
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
    public int semente = 101, sorteiaDesafio = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    private int resultadoInformado = 0, resultadoCerto = 0;
    public Button botaoZerar;

    ArrayList<MediaPlayer> mediaPlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio);

        //manter a tela ligada durante a atividade
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //capturando itens da tela
        conta = (TextView) findViewById(R.id.conta);
        resultado = (TextView) findViewById(R.id.resultado);
        botaoZerar = (Button) findViewById(R.id.botaoZerar);

        //recebendo dados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
        }

        //deixando o resultado preto para nao aparecer quando selecionado.
        if (!aluno.isComVideo()) {
            resultado.setTextColor(Color.BLACK);
        }

        //gerando a atividade conforme escolha
        if (desafio.getDesafio() == 1) {
            resultadoCerto = geraSoma();
        } else if (desafio.getDesafio() == 2) {
            resultadoCerto = geraSubtracao();
        } else if (desafio.getDesafio() == 3) {
            resultadoCerto = geraMultiplicacao();
        } else if (desafio.getDesafio() == 4) {
            resultadoCerto = geraDivisao();
        } else if (desafio.getDesafio() == 6) {//atividade desafio
            resultadoCerto = geraAleatorio();
        } else if (desafio.getDesafio() == 5) {//atividade contar
            resultadoCerto = geraContagem();
        }

        //pegando os sensores disponíveis no aparelho
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //armazenando apenas o sensor giroscopio
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

        //zerar resultado informado
        botaoZerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultadoInformado = 0;
                resultado.setText(String.valueOf(resultadoInformado));
                if (aluno.isComSom())
                    tocaSom(resultadoInformado);
            }
        });

        //aplicar resultado
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

    //quando a opção de som ta acionada, executa um alerta na tela que faz o talkback ser acionado.
    protected void tocaSom(final int resultado) {
        new Thread() {
            public void run() {
                Toast.makeText(TelaDesafio.this, "" + resultado, Toast.LENGTH_SHORT).show();
            }
        }.run();
    }

    //gerando as atividades
    public int geraContagem() {
        int valor = 0;
        Random random = new Random();
        valor = random.nextInt(semente);
        conta.setText("Atividade\nconte até\n" + valor);
        return valor;
    }

    public int geraAleatorio() {
        Random random = new Random();
        sorteiaDesafio = (random.nextInt(5) + 1);
        desafio.setSorteiaDesafio(sorteiaDesafio);
        if (sorteiaDesafio == 1) {
            resultadoCerto = geraSoma();
        } else if (sorteiaDesafio == 2) {
            resultadoCerto = geraSubtracao();
        } else if (sorteiaDesafio == 3) {
            resultadoCerto = geraMultiplicacao();
        } else if (sorteiaDesafio == 4) {
            resultadoCerto = geraDivisao();
        } else if (sorteiaDesafio == 5) {
            resultadoCerto = geraContagem();
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
        String palavra = aluno.getNomeAluno() + " resolva a atividade\nquanto é\n" + valor1 + " ÷ " + valor2 + " ?";
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
        String palavra = aluno.getNomeAluno() + " resolva a atividade\nquanto é\n" + valor1 + " × " + valor2 + " ?";
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

        String palavra = aluno.getNomeAluno() + " resolva a atividade\nquanto é\n" + valor1 + " - " + valor2 + " ?";
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
        String palavra = aluno.getNomeAluno() + " resolva a atividade\nquanto é\n" + valor1 + " + " + valor2 + " ?";
        conta.setText(palavra);

        //Toast.makeText(TelaDesafio.this, " " + valor1 + " + " + valor2 + " = " + (valor1 + valor2), Toast.LENGTH_SHORT).show();
        return (valor1 + valor2);
    }

    //recebendo resultados com base no sensor de movimentos
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            //cada evento de alteração nos eixos x e y são armazenados
            float x = event.values[0];
            float y = event.values[1];

            //as variavaies atuais foram criadas para aumentar ou diminuir a sensibilidade
            atualX = (float) x;
            atualY = (float) y;

            //adiciona as alterações em uma lista de float
            listaX.add(atualX);
            listaY.add(atualY);

            //tratando os valores do eixo x -- Dezenas
            if (listaX.size() > 40) {// quando a lista chega a 40 esse if é acionado
                listaX.remove(0);
                for (Float f : listaX) {
                    if (Math.abs(atualX - f) > 9.0) {
                        stepX++;// quando acontece uma diferença de giro relevante conta um stepX
                    }
                }
                if (stepX > 4) {//quando chega em quatro stepsX ele conta um Giro do Eixo X
                    stepsX = stepsX + 10; // contando eixo -- dezenas
                    listaX = new ArrayList<>();//esavasiando a listaX
                    vibrar();// aciona a vibração do celular

                    resultadoInformado = resultadoInformado + stepsX + stepsY;//armazenando resultado total informado até agora
                    resultado.setText(String.valueOf(resultadoInformado));//exibindo o resultado informado na tela
                    stepsX = 0;//zerar eixos
                    stepsY = 0;

                    // se foi com som aciona a função que pronuncia o número na hora do moviento.
                    if (aluno.isComSom())
                        tocaSom(resultadoInformado);//Toast.makeText(this, "" + resultadoInformado, Toast.LENGTH_SHORT).show();
                }
                stepX = 0;
                stepY = 0;// zerando o step y para evitar problema de movimento errado
            }
            //tratando os valores do eixo y -- unidades
            if (listaY.size() > 40) {//quando chega em quatro stepsY ele conta um Giro do Eixo Y
                listaY.remove(0);
                for (Float f : listaY) {
                    if (Math.abs(atualY - f) > 9.0) {
                        stepY++;
                    }
                }
                if (stepY > 4) {
                    stepsY++;// contando eixo -- unidade
                    listaY = new ArrayList<>();
                    vibrar();

                    resultadoInformado = resultadoInformado + stepsX + stepsY;
                    resultado.setText(String.valueOf(resultadoInformado));//exibindo o resultado informado na tela
                    stepsX = 0;
                    stepsY = 0;

                    // se foi com som aciona a função que pronuncia o número na hora do moviento.
                    if (aluno.isComSom())
                        tocaSom(resultadoInformado);//Toast.makeText(this, "" + resultadoInformado, Toast.LENGTH_SHORT).show();
                }
                stepY = 0;
                stepX = 0;// zerando o step x para evitar problema de movimento errado
            }

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
        long milliseconds = 60;//'60' é o tempo em milissegundos,duração da vibração.
        rr.vibrate(milliseconds);
    }
}
