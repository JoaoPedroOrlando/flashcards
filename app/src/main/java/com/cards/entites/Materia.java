package com.cards.entites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cards.entites.enums.FrequenciaMateria;
import com.cards.entites.enums.TipoMateria;

@Entity
public class Materia {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private boolean status;
    private String tipo;
    private String frequencia;

    public Materia(){}

    @Ignore
    public Materia(String nome, boolean status, String tipo, String frequencia) {

        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
        this.frequencia = frequencia;
    }

    @Ignore
    public Materia(int id, String nome, boolean status, String tipo, String frequencia) {
        this.id = id;
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

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public boolean getStatus() {return status;}

    public void setStatus(boolean status) {this.status = status;}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
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
