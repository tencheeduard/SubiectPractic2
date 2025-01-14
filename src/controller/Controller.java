package controller;

import model.Kunde;
import model.Produkt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    /**
     * Liste der Produkte, wovon Kunde kaufen konnen. Produkt name ist eindeutig.
     */
    List<Produkt> produkte = new ArrayList<>();


    /**
     * Liste der Kunden.
     */
    List<Kunde> kunden = new ArrayList<>();

    public List<Produkt> getProdukte()
    {
        return produkte;
    }


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
    public boolean updateProdukt(Produkt produkt, String newName, Integer newJahreszeit, Float newPreis)
    {

        if(produkt == null)
            return false;

        if(!newName.isEmpty())
            produkt.setName(newName);

        produkt.setJahreszeit( newJahreszeit );

        produkt.setPreis( newPreis );

        return true;

    }


    /**
     * Loscht einen Produkt von Produktliste. Gibt true zuruck wenn das erfolgreich war, und false wenn nicht.
     */
    public boolean removeProdukt(String name) {
        Produkt produkt = getProdukt(name);

        return produkte.remove(produkt);
    }

    /**
     * Fugt einen Kunde in die Liste hinzu. Gibt zuruck true wenn das erfolgreich geschafft war, und false wenn nicht.
     */
    public boolean addKunde(Kunde kunde)
    {
        if(kunden.stream().anyMatch( (k) -> k.getId().equals(kunde.getId())) )
            return false;

        return kunden.add(kunde);
    }

    /**
     * Erstellt einen neuen Kunde und fugt es zu die Liste hinzu.
     */

    // uhhh practic vorbind nu cred ca as folosi metoda asta vreodata pentru ca pnm, poti apela oricand constructorul
    // dar nu stiu daca ar depuncta pentru ca lipseste o parte din CRUD sau ceva.
    // asa ca o pun just in case
    public boolean createKunde(Integer id, String name, String ort)
    {
        Kunde kunde = new Kunde(id, name, ort, new ArrayList<>());

        return addKunde(kunde);
    }


    public Kunde getKunde(Integer id)
    {

        for(Kunde kunde : kunden)
            if(kunde.getId().equals(id))
                return kunde;

         return null;
    }

    /**
     * Updates einen Kunde.
     */
    public boolean updateKunde(Kunde kunde, String newName, String newOrt)
    {
        if(kunde == null)
            return false;

        if(!newName.isEmpty())
            kunde.setName(newName);

        if(!newOrt.isEmpty())
            kunde.setOrt(newOrt);


        return true;
    }


    /**
     * Loscht einen Kunde von Kundenliste.
     * @return true zuruck wenn das erfolgreich war, und false wenn nicht.
     */
    public boolean removeKunde(Integer id) {
        Kunde kunde = getKunde(id);

        return kunden.remove(kunde);
    }

    /**
     * Gibt zuruck den Anzahl der Kunden
     * @return einen Integer, der gleich zu die Lange der Kundenliste ist.
     */
    public Integer getKundenAnzahl()
    {
        return kunden.size();
    }

    public boolean produktEinkaufen(Kunde kunde, Produkt produkt)
    {
        if(!kunde.getProdukte().contains(produkt))
            return kunde.getProdukte().add(produkt);
        return false;
    }


    public List<Kunde> filterNachOrt(String ort)
    {
        return kunden.stream()
                .filter( (kunde)->kunde.getOrt().equals( ort ) )
                .toList();
    }

    public List<Kunde> kundeNachJahreszeit(Integer jahreszeit)
    {
        return kunden.stream()
                .filter( (kunde)-> kunde.getProdukte().stream()
                        .anyMatch( (produkt) -> produkt.getJahreszeit().equals(jahreszeit) ) )
                .toList();
    }

    public List<Produkt> produkteNachKunde(Kunde kunde, boolean ascending)
    {
        if(ascending)
            return kunde.getProdukte().stream()
                    .sorted( (produkt1, produkt2) -> produkt1.getName().compareTo(produkt2.getName()) )
                    .toList();

        return kunde.getProdukte().stream()
                .sorted( (produkt1, produkt2) -> produkt2.getName().compareTo(produkt1.getName()) )
                .toList();
    }
}
