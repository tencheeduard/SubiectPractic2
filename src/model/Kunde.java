package model;

import java.util.ArrayList;
import java.util.List;

public class Kunde {

    private Integer id;
    private String name;
    private String ort;
    private List<Produkt> produkte = new ArrayList<>();

    // click dreapta -> generate

    public Kunde() {}

    public Kunde(Integer id, String name, String ort, List<Produkt> produkte) {
        this.id = id;
        this.name = name;
        this.ort = ort;
        this.produkte = produkte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<Produkt> getProdukte() {
        return produkte;
    }

    public void setProdukte(List<Produkt> produkte) {
        this.produkte = produkte;
    }
}
