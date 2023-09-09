package com.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfiguracoesActivity extends AppCompatActivity{
    public static String ARQUIVO = "br.joaopedroorlando.flashcards.CONFIG_GERAIS";
    public static final String IDIOMA = "IDIOMA";
    public static final String ORDEM = "ORDEM";
    private Spinner spinnerIdioma;
    private Spinner spinnerOrdem;
    private String padraoIdioma = "ENG";
    private String padraoOrdem = "DESC";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // referenciando o spinner na classe
        this.spinnerIdioma = findViewById(R.id.spinnerIdioma);
        this.spinnerOrdem = findViewById(R.id.spinnerOdemListagem);
        //criando um array adapter a partir de um string array
        ArrayAdapter<CharSequence> arrayAdapterIdioma = ArrayAdapter.createFromResource(this,R.array.idioma,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> arrayAdapterOrdem =  ArrayAdapter.createFromResource(this,R.array.ordem_listagem,android.R.layout.simple_spinner_item);
        //especificando o layout
        arrayAdapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterOrdem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //vinculando o adapter ao spinner
        spinnerIdioma.setAdapter(arrayAdapterIdioma);
        spinnerOrdem.setAdapter(arrayAdapterOrdem);

        // definindo os listeners
        spinnerOrdem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                padraoOrdem = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spinnerIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                padraoIdioma = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        lerConfigs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_config,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemSalvarConfig){
            saveConfigs();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void lerConfigs(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        this.padraoIdioma = sharedPreferences.getString(IDIOMA,this.padraoIdioma);
        this.padraoOrdem = sharedPreferences.getString(ORDEM,this.padraoOrdem);

        this.defineValorSpinner(padraoIdioma,spinnerIdioma,getResources().getStringArray(R.array.idioma));
        this.defineValorSpinner(padraoOrdem,spinnerOrdem,getResources().getStringArray(R.array.ordem_listagem));
    }

    private void defineValorSpinner(String spinnerValue, Spinner spinner, String[] array){
        int selectedIndex = -1;
        for (int i = 0; i <array.length; i++) {
            if (array[i].toUpperCase().equals(spinnerValue)) {
                selectedIndex = i;
                break;
            }
        }
        if (selectedIndex != -1) {
            spinner.setSelection(selectedIndex);
        }
    }

    private void saveConfigs(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IDIOMA,this.padraoIdioma);
        editor.putString(ORDEM,this.padraoOrdem);
        editor.commit();
        Toast.makeText(getApplicationContext(), R.string.salvo_sucesso, Toast.LENGTH_SHORT).show();
    }
}