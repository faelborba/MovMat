package com.example.movmat;

import java.io.Serializable;

public class Aluno implements Serializable {
    public String nomeAluno = "";
    public int vitoriasSoma = 0, vitoriasSubtracao = 0, vitoriasMultiplicacao = 0, vitoriasDivisao = 0;
    public int totalSoma = 0, totalSubtracao = 0, totalMultiplicacao = 0, totalDivisao = 0;
    public boolean comVideo;
    public boolean comSom;

    public int getTotalSoma() {
        return totalSoma;
    }

    public void setTotalSoma(int totalSoma) {
        this.totalSoma = totalSoma + this.totalSoma;
    }

    public int getTotalSubtracao() {
        return totalSubtracao;
    }

    public void setTotalSubtracao(int totalSubtracao) {
        this.totalSubtracao = totalSubtracao;
    }

    public int getTotalMultiplicacao() {
        return totalMultiplicacao;
    }

    public void setTotalMultiplicacao(int totalMultiplicacao) {
        this.totalMultiplicacao = totalMultiplicacao;
    }

    public int getTotalDivisao() {
        return totalDivisao;
    }

    public void setTotalDivisao(int totalDivisao) {
        this.totalDivisao = totalDivisao;
    }

    public int getVitoriasSoma() {
        return vitoriasSoma;
    }

    public void setVitoriasSoma(int vitoriasSoma) {
        this.vitoriasSoma = vitoriasSoma + this.vitoriasSoma;
    }

    public int getVitoriasSubtracao() {
        return vitoriasSubtracao;
    }

    public void setVitoriasSubtracao(int vitoriasSubtracao) {
        this.vitoriasSubtracao = vitoriasSubtracao;
    }

    public int getVitoriasMultiplicacao() {
        return vitoriasMultiplicacao;
    }

    public void setVitoriasMultiplicacao(int vitoriasMultiplicacao) {
        this.vitoriasMultiplicacao = vitoriasMultiplicacao;
    }

    public int getVitoriasDivisao() {
        return vitoriasDivisao;
    }

    public void setVitoriasDivisao(int vitoriasDivisao) {
        this.vitoriasDivisao = vitoriasDivisao;
    }

    public boolean isComSom() {
        return comSom;
    }

    public void setComSom(boolean comSom) {
        this.comSom = comSom;
    }

    public boolean isComVideo() {
        return comVideo;
    }

    public void setComVideo(boolean comVideo) {
        this.comVideo = comVideo;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
}
