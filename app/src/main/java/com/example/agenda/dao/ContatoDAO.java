package com.example.agenda.dao;
import androidx.annotation.Nullable;

import com.example.agenda.models.Contato;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final static List<Contato> contatos = new ArrayList<>();
    private static int contadorIds = 1;

    public void Salvar(Contato contato) {
        incrementadorIDs(contato);
    }

    private void incrementadorIDs(Contato contato) {
        contato.SetId(contadorIds);
        contatos.add(contato);
        contadorIds++;
    }

    public void edita(Contato contato){
        Contato contatoSelecionado = getContatoById(contato);
        if(contatoSelecionado != null){
            int index = contatos.indexOf(contatoSelecionado);
            contatos.set(index, contato);
        }
    }

    @Nullable
    private Contato getContatoById(Contato contato) {
        for (Contato c : contatos){
            if(c.getId() == contato.getId()){
                return c;
            }
        }
        return null;
    }

    public List<Contato> getAll() {
        return new ArrayList<>(contatos);
    }

    public void remove(Contato contato) {
        Contato contatoSelecionado = getContatoById(contato);
        if(contatoSelecionado != null){
            contatos.remove(contatoSelecionado);
        }
    }
}
