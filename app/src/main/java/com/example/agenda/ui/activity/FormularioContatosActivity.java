package com.example.agenda.ui.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.agenda.R;
import com.example.agenda.dao.ContatoDAO;
import com.example.agenda.models.Contato;

public class FormularioContatosActivity extends AppCompatActivity {

    private EditText textNome;
    private EditText textTel;
    private EditText textEmail;
    private Contato contato;
    private final ContatoDAO dao = new ContatoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contatos);
        InicializaCampos();
        getDadosContato();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenuSelecionado = item.getItemId();
        if(idMenuSelecionado == R.id.menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDadosContato() {
        Intent dados = getIntent();
        if(dados.hasExtra("contato")) {
             contato = (Contato) dados.getSerializableExtra("contato");
            if(contato != null){
                setTitle("Edição de Contato");

                preencheCampos();
            }
        }else{
            setTitle("Novo Contato");
            contato = new Contato();
        }
    }

    private void preencheCampos() {
        textNome.setText(contato.getNome());
        textTel.setText(contato.getTelefone());
        textEmail.setText(contato.getEmail());
    }

    private void finalizaFormulario() {
        preencheContato();
        if(contato.temIdValido()){
            dao.edita(contato);
        }else{
            dao.Salvar(contato);
        }
        finish();
    }

    private void InicializaCampos() {
        textNome = findViewById(R.id.activity_formulario_contatos_nome);
        textTel = findViewById(R.id.activity_formulario_contatos_telefone);
        textEmail = findViewById(R.id.activity_formulario_contatos_email);
    }

    private void preencheContato() {
        String nome = textNome.getText().toString();
        String telefone = textTel.getText().toString();
        String email = textEmail.getText().toString();
        contato.setEmail(email);
        contato.setNome(nome);
        contato.setTelefone(telefone);
    }
}