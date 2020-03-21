package com.example.movmat;

public class Aluno {
    public String nomeAluno = "";
    public boolean comVideo;
    public boolean comSom;

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
