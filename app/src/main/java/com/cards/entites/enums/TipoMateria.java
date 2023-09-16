package com.cards.entites.enums;

public enum TipoMateria {
    FACULDADE,
    CONCURSO,
    COLÉGIO;

    public static String getString(TipoMateria enumValue){
        switch (enumValue){
            case COLÉGIO:
                return "COLÉGIO";
            case CONCURSO:
                return "CONCURSO";
            case FACULDADE:
                return "FACULDADE";
            default:
                return "SEM TIPO";
        }
    }

    public static TipoMateria getTipoMateria(String valor){
        switch (valor){
            case "COLÉGIO":
                return TipoMateria.COLÉGIO;
            case "CONCURSO":
                return TipoMateria.CONCURSO;
            case "FACULDADE":
                return TipoMateria.FACULDADE;
            default:
                return TipoMateria.COLÉGIO;
        }
    }
}
