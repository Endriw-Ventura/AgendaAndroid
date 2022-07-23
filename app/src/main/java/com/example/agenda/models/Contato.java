package com.example.agenda.models;
import androidx.annotation.NonNull;
import java.io.Serializable;

public class Contato implements Serializable {
    private  String nome;
    private  String telefone;
    private  String email;
    private int id = 0;

    public Contato() {

    }

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
