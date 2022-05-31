package com.example.agenda.ui.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.agenda.R;
import com.example.agenda.dao.ContatoDAO;
import com.example.agenda.models.Contato;

public class FormularioContatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contatos);
        ContatoDAO dao = new ContatoDAO();

        setTitle("Novo Contato");

        final EditText textNome = findViewById(R.id.activity_formulario_contatos_nome);
        final EditText textTel = findViewById(R.id.activity_formulario_contatos_telefone);
        final EditText textEmail = findViewById(R.id.activity_formulario_contatos_email);

        Button btnSalvar = findViewById(R.id.activity_formulario_contatos_btnSalvar);
        btnSalvar.setOnClickListener(view -> {
            String nome = textNome.getText().toString();
            String telefone = textTel.getText().toString();
            String email = textEmail.getText().toString();
            Contato contato = new Contato(nome, telefone, email);
            dao.Salvar(contato);

            finish();
        });
    }
}