package com.example.agenda.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agenda.R;
import com.example.agenda.dao.ContatoDAO;
import com.example.agenda.models.Contato;
import com.example.agenda.ui.adapter.ListaContatoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosActivity extends AppCompatActivity {
    private final ContatoDAO dao = new ContatoDAO();
    private ListaContatoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Lista de Contatos");
        configuraNovoContatoFAB();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_contatos_menu_context, menu);
    }

    private void configuraNovoContatoFAB() {
        FloatingActionButton btnAddContato = findViewById(R.id.btnAddContato);
        btnAddContato.setOnClickListener(view -> abreFormularioNovoContato());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Contato contatoEscolhido = adapter.getItem(menuInfo.position);
        int idMenuSelecionado = item.getItemId();
        switch (idMenuSelecionado){
            case R.id.menu_remover:
                DeleteContato(contatoEscolhido);
                break;
            case R.id.menu_editar:
                abreFormularioEdicaoContato(contatoEscolhido);
                break;
            default:
                break;
        }




        return super.onContextItemSelected(item);
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
        adapter.AtualizaLista(dao.getAll());
    }

    private void configuraLista() {
        ListView listadeContatos = findViewById(R.id.listView);
        preparaAdapter(listadeContatos);
        registerForContextMenu(listadeContatos);
    }

    private void DeleteContato(Contato contatoSelecionado) {
        dao.remove(contatoSelecionado);
        adapter.Remove(contatoSelecionado);
    }

    private void abreFormularioEdicaoContato(Contato contatoSelecionado) {
        Intent vaiParaFormularioActivity  = new Intent(ListaContatosActivity.this, FormularioContatosActivity.class);
        vaiParaFormularioActivity.putExtra("contato", contatoSelecionado);
        startActivity(vaiParaFormularioActivity);
    }


    private void preparaAdapter(ListView listadeContatos) {

        adapter = new ListaContatoAdapter(this);
        listadeContatos.setAdapter(adapter);
    }
}
