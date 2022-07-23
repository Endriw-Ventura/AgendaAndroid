package com.example.agenda.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agenda.R;
import com.example.agenda.dao.ContatoDAO;
import com.example.agenda.models.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class ListaContatosActivity extends AppCompatActivity {
    private final ContatoDAO dao = new ContatoDAO();
    private ArrayAdapter<Contato> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Lista de Contatos");
        configuraNovoContatoFAB();
        configuraLista();
    }

    private void configuraNovoContatoFAB() {
        FloatingActionButton btnAddContato = findViewById(R.id.btnAddContato);
        btnAddContato.setOnClickListener(view -> abreFormularioNovoContato());
    }

    private void abreFormularioNovoContato() {
        startActivity(new Intent(this, FormularioContatosActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AtualizaListaContatos();
    }

    private void AtualizaListaContatos() {
        adapter.clear();
        adapter.addAll(dao.getAll());
    }

    private void configuraLista() {
        ListView listadeContatos = findViewById(R.id.listView);
        preparaAdapter(listadeContatos);
        preparaLista(listadeContatos);
        excluiContato(listadeContatos);
    }

    private void excluiContato(ListView listadeContatos) {
        listadeContatos.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Contato contatoSelecionado = (Contato) adapterView.getItemAtPosition(position);
            DeleteContato(contatoSelecionado);
            return true;
        });
    }

    private void DeleteContato(Contato contatoSelecionado) {
        dao.remove(contatoSelecionado);
        adapter.remove(contatoSelecionado);
    }

    private void preparaLista(ListView listadeContatos) {
        listadeContatos.setOnItemClickListener((adapterView, view, position, id) -> {
            Contato contatoSelecionado = (Contato) adapterView.getItemAtPosition(position);
            abreFormularioEdicaoContato(contatoSelecionado);
        });
    }

    private void abreFormularioEdicaoContato(Contato contatoSelecionado) {
        Intent vaiParaFormularioActivity  = new Intent(ListaContatosActivity.this, FormularioContatosActivity.class);
        vaiParaFormularioActivity.putExtra("contato", contatoSelecionado);
        startActivity(vaiParaFormularioActivity);
    }


    private void preparaAdapter(ListView listadeContatos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listadeContatos.setAdapter(adapter);
    }
}
