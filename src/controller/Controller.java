package controller;

import model.Kunde;
import model.Produkt;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    /**
     * Liste der Produkte, wovon Kunde kaufen konnen. Produkt name ist eindeutig.
     */
    List<Produkt> produkte = new ArrayList<>();


    /**
     * Liste der Kunden.
     */
    List<Kunde> kunden = new ArrayList<>();


    /**
     * Fugt ein Produkt in die Liste hinzu. Gibt zuruck true wenn das erfolgreich geschafft war, und false wenn nicht.
     */
    public boolean addProdukt(Produkt produkt)
    {

        // anyMatch verifica daca oricare dintre elementele stream-ului indeplineste o conditie
        // in cazul asta, verificam daca exista deja un produs cu acelasi nume. daca da, returnam false.

        // alternativ, poti face un for in care sa verifici fiecare nume,
        // sau sa folosesti .filter() si sa verifici daca au ramas elemente in stream dupa filtrare


        // poti ignora asta pentru ca nu trebuie sa inveti acum tot despre java, dar cea mai eleganta metoda ar fi sa dai @Override la metoda de .equals() din Produkt, si doar sa folosesti produkte.contains(produkt). dar aia e alta poveste.

        if(produkte.stream().anyMatch( (p) -> p.getName().equals(produkt.getName())) )
            return false;

        return produkte.add(produkt);
    }

    /**
     * Erstellt einen neuen Produkt und fugt es zu die Liste hinzu.
     */

    // uhhh practic vorbind nu cred ca as folosi metoda asta vreodata pentru ca pnm, poti apela oricand constructorul
    // dar nu stiu daca ar depuncta pentru ca lipseste o parte din CRUD sau ceva.
    // asa ca o pun just in case
    public boolean createProdukt(String name, Float preis, Integer jahreszeit)
    {
        Produkt produkt = new Produkt(name, preis, jahreszeit);

        return addProdukt(produkt);
    }


    public Produkt getProdukt(String name)
    {
        // cautam primul (si singurul) produs cu numele dat

        return produkte.stream()
                .filter( (p) -> p.getName().equals(name) )
                .findFirst()


                // aici poate e putin confusing .orElse() asta, pentru ca nu cred ca ai mai auzit de el.

                // .findFirst() returneaza un Optional. Ceea ce inseamna ca daca gaseste produsul, il returneaza, dar daca nu, decizi tu ce faci.
                // in cazul asta, am ales sa returneze doar null.
                // dar poti folosi, de exemplu .orElseThrow(), ca sa dai throw exception
                .orElse(null);

        // aici defapt ar fi mai eficient sa faci un for si sa dai return cand gasesti primul, pentru ca stii ca ai doar un element cu numele ala.

        // am facut asa doar ca antrenament de streams

        // dar ar arata asa

        /*

        for(Produkt produkt : produkte)
            if(produkt.getName().equals(name))
                return produkt;

         return null;


         */
    }

    /**
     * Updates einen Produkt.
     */
    public boolean updateProdukt(Produkt produkt, Produkt newProdukt)
    {

        if(produkt == null)
            return false;

        produkt.setName( newProdukt.getName() );

        produkt.setJahreszeit( newProdukt.getJahreszeit() );

        produkt.setPreis( newProdukt.getPreis() );

        return true;

    }


    /**
     * Loscht einen Produkt von Produktliste. Gibt true zuruck wenn das erfolgreich war, und false wenn nicht.
     */
    public boolean removeProdukt(String name) {
        Produkt produkt = getProdukt(name);

        return produkte.remove(produkt);
    }

}
