package com.cards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cards.R;
import com.cards.entites.Materia;

import java.util.List;

public class MateriaAdapter extends BaseAdapter {

    private Context context;
    private List<Materia> materias;

    public MateriaAdapter(Context context, List<Materia> materiaList) {
        this.context = context;
        this.materias = materiaList;
    }

    private static class MateriaHolder{
        public TextView textViewValorMateria;
        public TextView textViewValorTipo;
        public TextView textViewValorFrequencia;
    }

    @Override
    public int getCount() {
        return this.materias.size();
    }

    @Override
    public Object getItem(int position) {
        return materias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MateriaHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lista_materia, parent, false);

            holder = new MateriaHolder();

            holder.textViewValorMateria = view.findViewById(R.id.textViewValorMateria);
            holder.textViewValorTipo = view.findViewById(R.id.textViewValorTipo);
            holder.textViewValorFrequencia = view.findViewById(R.id.textViewValorFrequencia);
            view.setTag(holder);
        } else {

            holder = (MateriaHolder) view.getTag();

        }

        holder.textViewValorMateria.setText(materias.get(position).getNome());

        switch(materias.get(position).getTipo()){
            case COLEGIO:
                holder.textViewValorTipo.setText(R.string.colegio);
                break;
            case CONCURSO:
                holder.textViewValorTipo.setText(R.string.concurso);
                break;
            case FACULDADE:
                holder.textViewValorTipo.setText(R.string.faculdade);
                break;
        }

        switch(materias.get(position).getFrequencia()){
            case DIARIO:
                holder.textViewValorFrequencia.setText(R.string.diario);
                break;
            case SEMANAL:
                holder.textViewValorFrequencia.setText(R.string.semanal);
                break;
            case MENSAL:
                holder.textViewValorFrequencia.setText(R.string.mensal);
                break;
        }

        return view;
    }
}
