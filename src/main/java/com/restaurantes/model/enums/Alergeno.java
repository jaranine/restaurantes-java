package com.restaurantes.model.enums;

public enum Alergeno {
    GLUTEN("Cereales con gluten"),
    CRUSTACEOS("Crustáceos"),
    HUEVOS("Huevos"),
    PESCADO("Pescado"),
    CACAHUETES("Cacahuetes"),
    SOJA("Soja"),
    LACTEOS("Leche y derivados (incluye lactosa)"),
    FRUTOS_SECOS("Frutos de cáscara (almendras, avellanas, nueces, etc.)"),
    APIO("Apio"),
    MOSTAZA("Mostaza"),
    SESAMO("Granos de sésamo"),
    SULFITOS("Dióxido de azufre y sulfitos"),
    ALTRAMUCES("Altramuces"),
    MOLUSCOS("Moluscos"),
    SIN_ALERGENOS("Sin alérgenos");

    private final String label;

    Alergeno(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}