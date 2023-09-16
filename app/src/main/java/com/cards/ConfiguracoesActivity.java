package com.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
    public static final String MODO = "MODO";
    private Spinner spinnerModo;
    private String padraoModo = "LIGHT";
    private String previousModeConfig;

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
        this.spinnerModo = findViewById(R.id.spinnerModo);
        //criando um array adapter a partir de um string array
        ArrayAdapter<CharSequence> arrayAdapterOrdem =  ArrayAdapter.createFromResource(this,R.array.theme_mode,android.R.layout.simple_spinner_item);
        //especificando o layout
        arrayAdapterOrdem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //vinculando o adapter ao spinner
        spinnerModo.setAdapter(arrayAdapterOrdem);

        // definindo os listeners
        spinnerModo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                padraoModo = parent.getItemAtPosition(position).toString();
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

        this.padraoModo = sharedPreferences.getString(MODO,this.padraoModo);
        this.previousModeConfig = sharedPreferences.getString(MODO,this.padraoModo);
        this.defineValorSpinner(padraoModo,spinnerModo,getResources().getStringArray(R.array.theme_mode));
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
        if(!this.padraoModo.equals(this.previousModeConfig)){
            editor.putString(MODO,this.padraoModo);
            editor.commit();
            if (this.padraoModo.equals("LIGHT")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (this.padraoModo.equals("DARK")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }

    }
}