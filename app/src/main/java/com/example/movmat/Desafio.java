package com.example.movmat;

import java.io.Serializable;

public class Desafio implements Serializable {
    public int desafio = 0, sorteiaDesafio = 0;
    public int resultadoInformado = 0;
    public int resultadoCerto = 0;

    public int getSorteiaDesafio() {
        return sorteiaDesafio;
    }

    public void setSorteiaDesafio(int sorteiaDesafio) {
        this.sorteiaDesafio = sorteiaDesafio;
    }

    public int getDesafio() {
        return desafio;
    }

    public void setDesafio(int desafio) {
        this.desafio = desafio;
    }

    public int getResultadoInformado() {
        return resultadoInformado;
    }

    public void setResultadoInformado(int resultadoInformado) {
        this.resultadoInformado = resultadoInformado;
    }

    public int getResultadoCerto() {
        return resultadoCerto;
    }

    public void setResultadoCerto(int resultadoCerto) {
        this.resultadoCerto = resultadoCerto;
    }
}
