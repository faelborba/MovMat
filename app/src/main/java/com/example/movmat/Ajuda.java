package com.example.movmat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ajuda extends AppCompatActivity {
    public String descricao1;
    public TextView texto;
    public Aluno aluno = new Aluno();
    public Desafio desafio = new Desafio();
    public int tela = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        texto = (TextView) findViewById(R.id.textoPrincipal);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            descricao1 = (String) getIntent().getSerializableExtra("descricao");
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            desafio = (Desafio) getIntent().getSerializableExtra("desafio");
            tela = (int) extras.getInt("tela");
        }
        if (tela == 1) {
            descricao1 = "Seja bem vindo(a) ao MovMat.\n" +
                    "MovMat é um aplicativo que visa auxiliar no ensino da matemática básica para criança deficiente visual em séries iniciais.\n" +
                    "Para iniciar clique em voltar em seu aparelho, digite o nome do aluno na caixa de texto e aperte ok.\n" +
                    "Boa sorte!\n";
        } else if (tela == 2) {
            descricao1 = "Ok, " + aluno.getNomeAluno() + " agora você precisa configurar as atividades, para isso você tem duas opções que devem ser marcadas conforme desejar.\n" +
                    "A primeira opção é definir se vão ser pronunciados os números ao mover o aparelho para resolver as atividades.\n" +
                    "A segunda opção você define se vai querer a exibição dos números ao mover o aparelho para resolver as atividades.\n" +
                    "Para isso volte, marque as opções desejadas e clique em ok.\n";
        } else if (tela == 3) {
            descricao1 = "Legal " + aluno.getNomeAluno() + ", agora você pode escolher a atividade que deseja praticar. " +
                    "São 3 atividades, a primeira é calcular composta pelas quatro operações de matemática básica, somar, subtrair, multiplicar e dividir. " +
                    "A segunda é uma atividade para você treinar a forma de contar.\n" +
                    "Por último uma atividade aleatória que sorteia uma das anteriores e você receberá um desafio surpresa." +
                    "Para responder as atividades você deve efetuar um semi-giro para um dos lados contando unidade ou um semi-giro para cima contando dezenas.\n" +
                    "Vamos lá?\n" +
                    "Para iniciar volte e escolha uma das atividades.\n";
        } else {
            descricao1 = "Texto sem explicação";
        }
        texto.setText(String.valueOf(descricao1));
    }

    public void onBackPressed() {
        Intent intent;
        // não chame o super desse método
        if (tela == 1) {
            intent = new Intent(this, MainActivity.class);
        } else if (tela == 2) {
            intent = new Intent(this, TelaConfiguracao.class);
            intent.putExtra("aluno", aluno);
        } else if (tela == 3) {
            intent = new Intent(this, SelecionaDesafio.class);
            intent.putExtra("aluno", aluno);
        } else {
            intent = new Intent(this, SelecionaDesafio.class);
            intent.putExtra("aluno", aluno);
        }

        startActivity(intent);
        super.onBackPressed();
    }
}
