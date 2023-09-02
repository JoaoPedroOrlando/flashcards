package com.cards.entites;

import com.cards.entites.enums.FrequenciaMateria;
import com.cards.entites.enums.TipoMateria;

public class Materia {

    private String nome;
    private Boolean status;
    private TipoMateria tipo;
    private FrequenciaMateria frequencia;

    public Materia(){}

    public Materia(String nome, Boolean status, TipoMateria tipo,FrequenciaMateria frequencia) {
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
        this.frequencia = frequencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoMateria getTipo() {
        return tipo;
    }

    public void setTipo(TipoMateria tipo) {
        this.tipo = tipo;
    }

    public FrequenciaMateria getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(FrequenciaMateria frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nome='" + nome + '\'' +
                ", status=" + status +
                ", tipo=" + tipo +
                ", frequencia=" + frequencia +
                '}';
    }
}
