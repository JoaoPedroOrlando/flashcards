package com.cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cards.adapters.MateriaAdapter;
import com.cards.entites.Materia;
import com.cards.entites.enums.FrequenciaMateria;
import com.cards.entites.enums.TipoMateria;
import com.cards.helpers.ThemePreferences;
import com.cards.repository.MateriaDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaMateriaActivity extends AppCompatActivity {

    private ListView listView;
    private List<Materia> listMaterias;
    private MateriaAdapter adapter;

    public static final int CADASTRAR_MATERIA = 1;

    public static final int ALTERAR_MATERIA = 2;

    private ActionMode actionMode;
    private int        posicaoSelecionada = -1;
    private View       viewSelecionada;


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.lista_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.menuItemEditar){
                editar();
                mode.finish();
                return true;
            } else if (item.getItemId() == R.id.menuItemExcluir){
                excluirMateriaSelecionada();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode         = null;
            viewSelecionada    = null;

            listView.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startConfigs();
        listView = findViewById(R.id.listViewMaterias);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        Materia materia = (Materia) listView.getItemAtPosition(position);

                        Toast.makeText(getApplicationContext(),
                                getString(R.string.materia_nome) + materia.getNome() + getString(R.string.foi_clicada),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                           View view,
                                           int position,
                                           long id)
                    {
                        if (actionMode != null){
                            return false;
                        }
                        posicaoSelecionada = position;
                        view.setBackgroundColor(Color.LTGRAY);
                        viewSelecionada = view;
                        listView.setEnabled(false);
                        actionMode = startSupportActionMode(mActionModeCallback);
                        return true;
            }
        });

        popularLista();
    }

    private void popularLista(){
        MateriaDatabase database = MateriaDatabase.getDatabase(this);
        this.listMaterias = database.materiaDao().queryAll();
        adapter = new MateriaAdapter(this,this.listMaterias);
        listView.setAdapter(adapter);

    }

    public void editar(){
        Materia materia = listMaterias.get(posicaoSelecionada);
        CadastraMateriaActivity.alterarMateria(this,ALTERAR_MATERIA,materia);
    }

    public void excluirMateriaSelecionada(){
        Materia materia = listMaterias.get(posicaoSelecionada);
        listMaterias.remove(posicaoSelecionada);
        MateriaDatabase database = MateriaDatabase.getDatabase(this);
        database.materiaDao().delete(materia);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CADASTRAR_MATERIA || requestCode == ALTERAR_MATERIA) &&
                resultCode == Activity.RESULT_OK){
            popularLista();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuItemAdicionar){
            CadastraMateriaActivity.novaMateria(this,CADASTRAR_MATERIA);
            return true;
        }else if(id == R.id.menuItemSobre){
            Intent intent = new Intent(this,
                    SobreActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.menuItemConfiguracoes){
            Intent intent = new Intent(this,
                    ConfiguracoesActivity.class);
            startActivity(intent);
            return true;
        }else {
            return true;
        }
    }


    private void startConfigs(){
        String savedTheme = ThemePreferences.getSavedTheme(this,"LIGHT");
        if(savedTheme.equals("DARK"))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else if(savedTheme.equals("LIGHT"))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_lista_materia);
    }
}