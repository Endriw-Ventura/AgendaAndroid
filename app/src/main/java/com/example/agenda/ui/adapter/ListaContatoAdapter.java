package com.example.agenda.ui.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.agenda.R;
import com.example.agenda.models.Contato;
import java.util.ArrayList;
import java.util.List;

public class ListaContatoAdapter extends BaseAdapter {

    List<Contato> contatos = new ArrayList<>();
    private Context context;

    public ListaContatoAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contatos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = CriaView(viewGroup);
        Contato contato = contatos.get(i);
        SetDataContato(viewCriada, contato);
        return viewCriada;
    }

    private void SetDataContato(View viewCriada, Contato contato) {
        TextView campoNome = viewCriada.findViewById(R.id.tv_contact_name);
        TextView campoTelefone = viewCriada.findViewById(R.id.tv_contact_phone);
        campoNome.setText(contato.getNome());
        campoTelefone.setText(contato.getTelefone());
    }

    private View CriaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_contact_layout, viewGroup, false);
    }

    private void Clear(){
        contatos.clear();
    }

    public void Remove(Contato contato){
        contatos.remove(contato);
        notifyDataSetChanged();
    }

    private void AddAll(List<Contato> all) {
        contatos.addAll(all);
    }

    public void AtualizaLista(List<Contato> listaContatos){
        contatos.clear();
        contatos.addAll(listaContatos);
        notifyDataSetChanged();
    }
}
