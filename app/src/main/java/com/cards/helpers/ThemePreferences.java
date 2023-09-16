package com.cards.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemePreferences {

    private static final String ARQUIVO_CONFIGURACOES = "br.joaopedroorlando.flashcards.CONFIG_GERAIS";
    private static final String MODO = "MODO";

    public static String getSavedTheme(Context context, String modo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ARQUIVO_CONFIGURACOES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MODO, modo);
    }
}
