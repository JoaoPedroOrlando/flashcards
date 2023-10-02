package com.cards.entites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cards.entites.enums.FrequenciaMateria;
import com.cards.entites.enums.TipoMateria;

@Entity
public class Materia {

    public static final int TIPO_COLEGIO = 1;
    public static final int TIPO_FACULDADE = 2;
    public static final int TIPO_CONCURSO = 3;
    public static final int FREQUENCIA_DIARIA = 1;
    public static final int FREQUENCIA_SEMANAL = 2;
    public static final int FREQUENCIA_MENSAL = 3;



    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private boolean status;
    private int tipo;
    private int frequencia;
    private long criadoem;
    private long atualizadoem;



    public Materia(){}

    @Ignore
    public Materia(String nome, boolean status, int tipo, int frequencia, long criadoem, long atualizadoem) {
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
        this.frequencia = frequencia;
        this.criadoem = criadoem;
        this.atualizadoem = atualizadoem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public boolean getStatus() {return status;}

    public void setStatus(boolean status) {this.status = status;}

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public long getCriadoem() {
        return criadoem;
    }

    public void setCriadoem(long criadoem) {
        this.criadoem = criadoem;
    }

    public long getAtualizadoem() {
        return atualizadoem;
    }

    public void setAtualizadoem(long atualizadoem) {
        this.atualizadoem = atualizadoem;
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

    public static int converteFrequencia(String freq){
        switch (freq.toUpperCase()){
            case "DIÁRIO":
            case "DAILY":
                return 1;
            case "SEMANAL":
            case "WEEKLY":
                return 2;
            case "MENSAL":
            case "MONTHLY":
                return 3;
            default:
                return 1;
        }
    }

    public static int converteTipo(String tipo){
        switch (tipo.toUpperCase()){
            case "COLÉGIO":
            case "SCHOOL":
                return 1;
            case "COLLEGE":
            case "FACULDADE":
                return 2;
            case "CONCURSO":
            case "PUBLIC TENDER":
                return 3;
            default:
                return 1;
        }
    }
}
