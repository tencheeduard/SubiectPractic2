package view;

import controller.Controller;
import model.Kunde;
import model.Produkt;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    Controller controller = new Controller();

    public void MainPage() {
        // nu prea exista metoda de sters consola in java, asa ca facem asa
        System.out.println("\n\n\n\n");
        System.out.println("SPORTARTIKELGESCHAFT");
        System.out.println("");
        System.out.println("");
        System.out.println("1. PRODUKTE");
        System.out.println("2. KUNDEN");

        Scanner scanner = new Scanner(System.in);
        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            ProduktePage();
        if (input == 2)
            KundenPage();
    }

    public void ProduktePage() {
        System.out.println("\n\n\n\n");
        System.out.println("PRODUKTE");
        System.out.println("");
        System.out.println("");
        System.out.println("1. CREATE");
        System.out.println("2. READ");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. ZURUCK");

        Scanner scanner = new Scanner(System.in);

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            CreateProduktPage();
        else if (input == 2)
            ReadProduktPage();
        else if (input == 3)
            UpdateProduktPage();
        else if (input == 4)
            RemoveProduktPage();
        else if (input == 5)
            MainPage();


    }

    public void CreateProduktPage() {
        System.out.println("\n\n\n\n");
        System.out.println("CREATE PRODUKT");
        System.out.println("");
        System.out.println("");
        System.out.println("PRODUKT NAME: ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        scanner.reset();

        System.out.println("PREIS: ");
        Float preis = Float.parseFloat(scanner.nextLine());
        scanner.reset();

        System.out.println("JAHRESZEIT: 1. WINTER 2. FRUHLING 3. SOMMER 4. HERBST: ");
        Integer jahreszeit = Integer.parseInt(scanner.nextLine());

        boolean output = controller.createProdukt(name, preis, jahreszeit);

        String outputString = "";
        if (output)
            outputString = "PRODUKT ERFOLGREICH ERSTELLT!";
        else
            outputString = "MAN KONNTE DEN PRODUKT NICHT ERSTELLEN";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        System.out.println("2. WIEDERVERSUCHEN");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            ProduktePage();
        else if (input == 2)
            CreateProduktPage();
    }

    public void ReadProduktPage() {
        System.out.println("\n\n\n\n");
        System.out.println("READ PRODUKT");
        System.out.println("");
        System.out.println("");
        System.out.println("PRODUKT NAME: ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        scanner.reset();

        Produkt output = controller.getProdukt(name);

        if (output == null) {
            System.out.println("MAN KONNTE DEN PRODUKT NICHT FINDEN");
            System.out.println("");
            System.out.println("1. ZURUCK");
            System.out.println("2. WIEDERVERSUCHEN");

            scanner.reset();

            String input = scanner.nextLine();

            Integer inputInt = Integer.parseInt(input);

            if (inputInt == 1)
                ProduktePage();
            if (inputInt == 2)
                ReadProduktPage();

            // aici e important return, altfel va incerca sa continue
            return;
        }

        String jahreszeit = "";

        switch (output.getJahreszeit()) {
            case 0:
                jahreszeit = "WINTER";
                break;
            case 1:
                jahreszeit = "FRUHLING";
                break;
            case 2:
                jahreszeit = "SOMMER";
                break;
            case 3:
                jahreszeit = "WINTER";
                break;
        }

        System.out.println("NAME: " + output.getName());
        System.out.println("PREIS: " + output.getPreis());
        System.out.println("JAHRESZEIT: " + jahreszeit);
        System.out.println("");
        System.out.println("1. ZURUCK");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            ProduktePage();
    }

    void UpdateProduktPage()
    {
        // refolosim mult cod de la read produkt
        System.out.println("\n\n\n\n");
        System.out.println("UPDATE PRODUKT");
        System.out.println("");
        System.out.println("");
        System.out.println("PRODUKT NAME: ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        Produkt produkt = controller.getProdukt(name);

        if (produkt == null) {
            System.out.println("MAN KONNTE DEN PRODUKT NICHT FINDEN");
            System.out.println("");
            System.out.println("1. ZURUCK");
            System.out.println("2. WIEDERVERSUCHEN");

            scanner.reset();

            Integer input = Integer.parseInt(scanner.nextLine());

            if (input == 1)
                ProduktePage();
            if (input == 2)
                UpdateProduktPage();

            // aici e important return, altfel va incerca sa continue
            return;
        }

        scanner.reset();

        System.out.println("NEUEN NAME (LEER UM NICHT ZU MODIFIZIEREN):");
        scanner.reset();
        String newName = scanner.nextLine();


        System.out.println("NEUEN PREIS (LEER UM NICHT ZU MODIFIZIEREN):");
        scanner.reset();
        String newPreis = scanner.nextLine();

        System.out.println("NEUEN JAHRESZEIT (LEER UM NICHT ZU MODIFIZIEREN):");
        scanner.reset();
        String newJahreszeit = scanner.nextLine();

        Produkt updatedProdukt = new Produkt();

        Integer jahreszeit;
        Float preis;

        if(!newPreis.isEmpty())
            preis = Float.parseFloat(newPreis);
        else
            preis = produkt.getPreis();

        if(!newJahreszeit.isEmpty())
            jahreszeit = Integer.parseInt(newJahreszeit);
        else
            jahreszeit = produkt.getJahreszeit();

        boolean output = controller.updateProdukt(produkt, newName, jahreszeit, preis);

        String outputString = "";
        if(output == true)
            outputString = "PRODUKT ERFOLGREICH AKTUALISIERT!";
        else
            outputString = "MAN KONNTE DEN PRODUKT NICHT AKTUALISIEREN.";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            ProduktePage();
    }

    void RemoveProduktPage()
    {
        System.out.println("\n\n\n\n");
        System.out.println("PRODUKT LOSCHEN");
        System.out.println("");
        System.out.println("");
        System.out.println("PRODUKT NAME: ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        boolean output = controller.removeProdukt(name);

        String outputString = "";
        if(output == true)
            outputString = "PRODUKT ERFOLGREICH GELOSCHT!";
        else
            outputString = "MAN KONNTE DEN PRODUKT NICHT LOSCHEN.";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        System.out.println("2. WIEDERVERSUCHEN");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            ProduktePage();
        else if (input == 2)
            RemoveProduktPage();
    }


    public void KundenPage() {
        System.out.println("\n\n\n\n");
        System.out.println("KUNDEN");
        System.out.println("");
        System.out.println("");
        System.out.println("1. CREATE");
        System.out.println("2. READ");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. PRODUKTE EINKAUFEN");
        System.out.println("6. ZURUCK");

        Scanner scanner = new Scanner(System.in);

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            CreateKundePage();
        else if (input == 2)
            ReadKundePage();
        else if (input == 3)
            UpdateKundePage();
        else if (input == 4)
            RemoveKundePage();
        else if (input == 5)
            ProdukteEinkaufenPage();
        else if (input == 6)
            MainPage();


    }

    public void CreateKundePage() {
        System.out.println("\n\n\n\n");
        System.out.println("CREATE KUNDE");
        System.out.println("");
        System.out.println("");
        System.out.println("KUNDE NAME: ");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        scanner.reset();

        System.out.println("ORT: ");
        String ort = scanner.nextLine();
        scanner.reset();

        Integer id = controller.getKundenAnzahl();

        boolean output = controller.createKunde(id, name, ort);

        String outputString = "";
        if (output)
            outputString = "KUNDE ERFOLGREICH MIT ID " + id + " REGISTRIERT!";
        else
            outputString = "MAN KONNTE DEN KUNDE NICHT REGISTRIEREN";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        System.out.println("2. WIEDERVERSUCHEN");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            KundenPage();
        else if (input == 2)
            CreateKundePage();
    }

    public void ReadKundePage() {
        System.out.println("\n\n\n\n");
        System.out.println("READ KUNDE");
        System.out.println("");
        System.out.println("");
        System.out.println("KUNDE ID: ");

        Scanner scanner = new Scanner(System.in);

        Integer id = Integer.parseInt(scanner.nextLine());

        Kunde output = controller.getKunde(id);

        if (output == null) {
            System.out.println("MAN KONNTE DEN KUNDEN NICHT FINDEN");
            System.out.println("");
            System.out.println("1. ZURUCK");
            System.out.println("2. WIEDERVERSUCHEN");

            String input = scanner.nextLine();

            Integer inputInt = Integer.parseInt(input);

            if (inputInt == 1)
                KundenPage();
            if (inputInt == 2)
                ReadKundePage();

            // aici e important return, altfel va incerca sa continue
            return;
        }

        System.out.println("NAME: " + output.getName());
        System.out.println("ORT: " + output.getOrt());
        System.out.println("");
        System.out.println("1. ZURUCK");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            KundenPage();
    }

    void UpdateKundePage()
    {
        System.out.println("\n\n\n\n");
        System.out.println("UPDATE KUNDE");
        System.out.println("");
        System.out.println("");
        System.out.println("KUNDEN ID: ");

        Scanner scanner = new Scanner(System.in);

        Integer id = Integer.parseInt(scanner.nextLine());

        Kunde kunde = controller.getKunde(id);

        if (kunde == null) {
            System.out.println("MAN KONNTE DEN KUNDEN NICHT FINDEN");
            System.out.println("");
            System.out.println("1. ZURUCK");
            System.out.println("2. WIEDERVERSUCHEN");

            scanner.reset();

            Integer input = Integer.parseInt(scanner.nextLine());

            if (input == 1)
                KundenPage();
            if (input == 2)
                UpdateKundePage();

            // aici e important return, altfel va incerca sa continue
            return;
        }

        scanner.reset();

        System.out.println("NEUEN NAME (LEER UM NICHT ZU MODIFIZIEREN):");
        scanner.reset();
        String newName = scanner.nextLine();


        System.out.println("NEUEN ORT (LEER UM NICHT ZU MODIFIZIEREN):");
        scanner.reset();
        String newOrt = scanner.nextLine();

        boolean output = controller.updateKunde(kunde, newName, newOrt);

        String outputString = "";
        if(output == true)
            outputString = "KUNDE ERFOLGREICH AKTUALISIERT!";
        else
            outputString = "MAN KONNTE DEN KUNDEN NICHT AKTUALISIEREN.";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            KundenPage();
    }

    void RemoveKundePage()
    {
        System.out.println("\n\n\n\n");
        System.out.println("KUNDE LOSCHEN");
        System.out.println("");
        System.out.println("");
        System.out.println("KUNDE ID: ");

        Scanner scanner = new Scanner(System.in);

        Integer id = Integer.parseInt(scanner.nextLine());

        boolean output = controller.removeKunde(id);

        String outputString = "";
        if(output == true)
            outputString = "KUNDE ERFOLGREICH GELOSCHT!";
        else
            outputString = "MAN KONNTE DEN KUNDEN NICHT LOSCHEN.";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        System.out.println("2. WIEDERVERSUCHEN");
        scanner.reset();

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 1)
            KundenPage();
        else if (input == 2)
            RemoveKundePage();
    }

    void ProdukteEinkaufenPage()
    {
        System.out.println("\n\n\n\n");
        System.out.println("PRODUKTE EINKAUFEN");
        System.out.println("");
        System.out.println("");
        System.out.println("KUNDE ID: ");

        Scanner scanner = new Scanner(System.in);

        Integer id = Integer.parseInt(scanner.nextLine());

        Kunde kunde = controller.getKunde(id);

        if (kunde == null) {
            System.out.println("MAN KONNTE DEN KUNDEN NICHT FINDEN");
            System.out.println("");
            System.out.println("1. ZURUCK");
            System.out.println("2. WIEDERVERSUCHEN");

            scanner.reset();

            Integer input = Integer.parseInt(scanner.nextLine());

            if (input == 1)
                KundenPage();
            if (input == 2)
                ProdukteEinkaufenPage();

            // aici e important return, altfel va incerca sa continue
            return;
        }

        List<Produkt> produkte = controller.getProdukte();

        System.out.println("WAHLE EIN PRODUKT ZUM EINKAUFEN:");
        System.out.println("");
        System.out.println("0. ZURUCK");
        // printeaza fiecare produs si indexul lui (+1) inainte
        for(int i = 0; i < produkte.size(); i++)
            System.out.println(i + 1 + ". " + produkte.get(i).getName());

        Integer input = Integer.parseInt(scanner.nextLine());

        if (input == 0)
            KundenPage();
        else {
            // cumparam produsul de la index-ul dat

            controller.produktEinkaufen(kunde, produkte.get(input - 1));
            KundenPage();
        }
    }

}
