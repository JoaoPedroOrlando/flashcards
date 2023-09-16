package com.cards.entites.enums;

public enum FrequenciaMateria {
    DIARIO,
    SEMANAL,
    MENSAL;

    public static String getString(FrequenciaMateria enumValue){
        switch (enumValue){
            case DIARIO:
                return "DIARIO";
            case SEMANAL:
                return "SEMANAL";
            case MENSAL:
                return "MENSAL";
            default:
                return "SEM TIPO";
        }
    }

    public static FrequenciaMateria getTipoMateria(String valor){
        switch (valor){
            case "DIARIO":
                return FrequenciaMateria.DIARIO;
            case "SEMANAL":
                return FrequenciaMateria.SEMANAL;
            case "MENSAL":
                return FrequenciaMateria.MENSAL;
            default:
                return FrequenciaMateria.DIARIO;
        }
    }
}
