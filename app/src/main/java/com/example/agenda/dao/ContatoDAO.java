package com.example.agenda.dao;
import com.example.agenda.models.Contato;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final static List<Contato> contatos = new ArrayList<>();

    public void Salvar(Contato contato) {
        contatos.add(contato);
    }

    public List<Contato> getAll() {
        return new ArrayList<>(contatos);
    }
}
