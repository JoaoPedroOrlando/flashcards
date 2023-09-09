package com.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cards.entites.Materia;
import com.cards.entites.enums.FrequenciaMateria;
import com.cards.entites.enums.TipoMateria;

public class CadastraMateriaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText et_materia;
    private RadioGroup rg_frequencia;
    private RadioButton rb_frequencia;
    private String tipoMateria;
    private CheckBox cb_ativo;

    private int modo;

    public static final String MATERIA = "MATERIA";
    public static final String TIPO ="TIPO";
    public static final String FREQUENCIA = "FREQUENCIA";
    public static final String ATIVO = "ATIVO";

    public static final String MODO    = "MODO";

    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    public static void novaMateria(AppCompatActivity activity){

        Intent intent = new Intent(activity, CadastraMateriaActivity.class);

        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, NOVO);
    }

    public static void alterarMateria(AppCompatActivity activity, Materia materia){

        Intent intent = new Intent(activity, CadastraMateriaActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(MATERIA, materia.getNome());
        intent.putExtra(FREQUENCIA, materia.getFrequencia().toString());
        intent.putExtra(ATIVO, materia.getStatus());
        intent.putExtra(TIPO, materia.getTipo().toString());

        activity.startActivityForResult(intent, ALTERAR);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INPUT NOME DA MATERIA
        et_materia = findViewById(R.id.et_materia);

        //RADIO GROUP E BUTTON
        rg_frequencia = findViewById(R.id.radio_group_frequencia);

        //SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinner_tipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //CHECKBOX
        cb_ativo = findViewById(R.id.checkbox_ativo);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            modo = bundle.getInt(MODO, NOVO);
            if (modo == ALTERAR)
                popularCampos(bundle);
        }

    }

    public void cleanFields( ){
        //limpa texto do input
        et_materia.setText(null);
        //remove check do radio button
        int radioId = rg_frequencia.getCheckedRadioButtonId();
        rb_frequencia = findViewById(radioId);
        rb_frequencia.setChecked(false);
        //remove check do checkbox
        cb_ativo.setChecked(false);
        //selecionando primeiro valor do spinner
        Spinner spinnerTipo = findViewById(R.id.spinner_tipo);
        spinnerTipo.setSelection(0);

    }

    public void saveSubject(){
        //input
        String subject = et_materia.getText().toString();
        if(subject == null || subject.trim().isEmpty()){
            Toast.makeText(this, R.string.erro_materia, Toast.LENGTH_LONG).show();
            et_materia.requestFocus();
            return;
        }
        //radio button
        int radioId = rg_frequencia.getCheckedRadioButtonId();
        rb_frequencia = findViewById(radioId);
        if(!rb_frequencia.isChecked()){
            Toast.makeText(this, R.string.erro_frequencia_estudo, Toast.LENGTH_LONG).show();
            rg_frequencia.requestFocus();
            return;
        }
        cb_ativo = findViewById(R.id.checkbox_ativo);
        String rbTxt = rb_frequencia.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MATERIA,subject);
        intent.putExtra(TIPO,this.tipoMateria);
        intent.putExtra(FREQUENCIA,rbTxt);
        intent.putExtra(ATIVO, cb_ativo.isActivated());

        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        this.tipoMateria = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onCheckRadioButton(View view){
        int radioId = rg_frequencia.getCheckedRadioButtonId();
        rb_frequencia = findViewById(radioId);
    }

    @Override
    public void onBackPressed(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_materia,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemSalvar){
            saveSubject();
            return true;
        }else if(item.getItemId() == R.id.menuItemLimpar){
            cleanFields();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    public void popularCampos(Bundle bundle){
        // TIPO - SPINNER
        String spinnerValue = bundle.getString(TIPO).toUpperCase();
        Spinner spinner = findViewById(R.id.spinner_tipo);
        String[] tipoArray = getResources().getStringArray(R.array.tipo_array);
        int selectedIndex = -1;
        for (int i = 0; i < tipoArray.length; i++) {
            if (tipoArray[i].toUpperCase().equals(spinnerValue)) {
                selectedIndex = i;
                break;
            }
        }
        if (selectedIndex != -1) {
            spinner.setSelection(selectedIndex);
        }

        // FREQUENCIA - RADIO GROUP
        RadioGroup radioGroup = findViewById(R.id.radio_group_frequencia);
        int radioGroupId = -1;
        switch(bundle.getString(FREQUENCIA).toUpperCase()){
            case "DIÁRIO":
                radioGroupId = R.id.radio_diario;
                break;
            case "SEMANAL":
                radioGroupId = R.id.radio_semanal;
                break;
            case "MENSAL":
                radioGroupId = R.id.radio_mensal;
                break;
        }
        if(radioGroupId != -1){
            radioGroup.check(radioGroupId);
        }

        // MATÉRIA - EDITTEXT
        et_materia.setText(bundle.getString(MATERIA));

        // ATIVO - CHECKBOX
        if (bundle.getBoolean(ATIVO)){
            cb_ativo.setChecked(true);
        } else{
            cb_ativo.setChecked(false);
        }
    }

}