package com.example.movmat;

import java.io.Serializable;
//classe criada para manipular os dados referentes ao aluno
public class Aluno implements Serializable {
    public String nomeAluno = "";
    public int vitoriasSoma = 0, vitoriasSubtracao = 0, vitoriasMultiplicacao = 0, vitoriasDivisao = 0, vitoriaContagem = 0;
    public int totalSoma = 0, totalSubtracao = 0, totalMultiplicacao = 0, totalDivisao = 0, totalContagem = 0;
    public boolean comVideo;
    public boolean comSom;

    public int getVitoriaContagem() {
        return vitoriaContagem;
    }

    public void setVitoriaContagem(int vitoriaContagem) {
        this.vitoriaContagem = vitoriaContagem + this.vitoriaContagem;
    }

    public int getTotalContagem() {
        return totalContagem;
    }

    public void setTotalContagem(int totalContagem) {
        this.totalContagem = totalContagem + this.totalContagem;
    }

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
        this.totalSubtracao = totalSubtracao + this.totalSubtracao;
    }

    public int getTotalMultiplicacao() {
        return totalMultiplicacao;
    }

    public void setTotalMultiplicacao(int totalMultiplicacao) {
        this.totalMultiplicacao = totalMultiplicacao + this.totalMultiplicacao;
    }

    public int getTotalDivisao() {
        return totalDivisao;
    }

    public void setTotalDivisao(int totalDivisao) {
        this.totalDivisao = totalDivisao + this.totalDivisao;
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
        this.vitoriasSubtracao = vitoriasSubtracao + this.vitoriasSubtracao;
    }

    public int getVitoriasMultiplicacao() {
        return vitoriasMultiplicacao;
    }

    public void setVitoriasMultiplicacao(int vitoriasMultiplicacao) {
        this.vitoriasMultiplicacao = vitoriasMultiplicacao + this.vitoriasMultiplicacao;
    }

    public int getVitoriasDivisao() {
        return vitoriasDivisao;
    }

    public void setVitoriasDivisao(int vitoriasDivisao) {
        this.vitoriasDivisao = vitoriasDivisao + this.vitoriasDivisao;
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
