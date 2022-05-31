package com.example.agenda.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agenda.R;
import com.example.agenda.dao.ContatoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaContatosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Lista de Contatos");
        FloatingActionButton btnAddContato = findViewById(R.id.btnAddContato);

        btnAddContato.setOnClickListener(view -> startActivity(new Intent(ListaContatosActivity.this, FormularioContatosActivity.class)));


    }

    @Override
    protected void onResume() {
        super.onResume();

        ContatoDAO dao = new ContatoDAO();
        ListView listadeContatos = findViewById(R.id.listView);

        listadeContatos.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getAll()));
    }
}
