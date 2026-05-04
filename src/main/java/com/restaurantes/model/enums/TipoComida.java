package com.restaurantes.model.enums;

public enum TipoComida {
    ESP("Española"),
    JAP("Japonesa"),
    ITA("Italiana"),
    MEX("Mexicana"),
    AME("Americana"),
    OTRO("Otro");

    private final String label;

    TipoComida(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
