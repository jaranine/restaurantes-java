package com.restaurantes.model.enums;

public enum TipoPlato {
    ENTRANTES("Entrantes"),
    PRIMER_PLATO("Primer plato"),
    SEGUNDO_PLATO("Segundo plato"),
    POSTRE("Postre");

    private final String label;

    TipoPlato(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
