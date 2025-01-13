package model;

public class Produkt {

    private String name;
    private Float preis;

    /**
     * Jahreszeit
     * 0 = WINTER
     * 1 = FRUHLING
     * 2 = SOMMER
     * 3 = HERBST
     */
    private Integer jahreszeit; // aici cred ca poti alege tu cum reprezinti... eu am ales un int care sa fie de la 0-3.
                        // poti face cu un string

                        // cea mai buna reprezentare ar fi cu un enum, daca vrei iti arat, dar am zis sa nu bag prea multe informatii.


    // sa folosesti click dreapta -> Generate pentru astea ca sa economisesti timp! (daca esti in IntelliJ)

    public Produkt() {}

    public Produkt(String name, Float preis, Integer jahreszeit) {
        this.name = name;
        this.preis = preis;

        if(jahreszeit >= 0 && jahreszeit < 4)
            this.jahreszeit = jahreszeit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
    }

    public Integer getJahreszeit() {
        return jahreszeit;
    }

    public void setJahreszeit(Integer jahreszeit) {
        if(jahreszeit >= 0 && jahreszeit < 4)
            this.jahreszeit = jahreszeit;
    }
}
