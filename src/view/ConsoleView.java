package view;

import controller.Controller;
import model.Produkt;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class ConsoleView {

    Controller controller = new Controller();

    public void MainPage() {
        // nu prea exista metoda de sters consola in java, asa ca facem asa
        System.out.println("\n\n\n\n");
        System.out.println("SPORTARTIKELGESCHAFT");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Produkte");

        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        if (input == 1)
            ProduktePage();
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

        Scanner scanner = new Scanner(System.in);

        Integer input = scanner.nextInt();

        if (input == 1)
            CreateProduktPage();
        else if (input == 2)
            ReadProduktPage();
        else if (input == 3)
            UpdateProduktPage();
        else if (input == 4)
            RemoveProduktPage();

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

        Integer input = scanner.nextInt();

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

        Integer input = scanner.nextInt();

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

            Integer input = scanner.nextInt();

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

        if(!newName.isEmpty())
            updatedProdukt.setName(newName);
        else
            updatedProdukt.setName(produkt.getName());

        if(!newPreis.isEmpty())
            updatedProdukt.setPreis(Float.parseFloat(newPreis));
        else
            updatedProdukt.setPreis(produkt.getPreis());

        if(!newJahreszeit.isEmpty())
            updatedProdukt.setJahreszeit(Integer.parseInt(newJahreszeit));
        else
            updatedProdukt.setJahreszeit(produkt.getJahreszeit());

        boolean output = controller.updateProdukt(produkt, updatedProdukt);

        String outputString = "";
        if(output == true)
            outputString = "PRODUKT ERFOLGREICH AKTUALISIERT!";
        else
            outputString = "MAN KONNTE DEN PRODUKT NICHT AKTUALISIEREN.";

        System.out.println(outputString);
        System.out.println("");
        System.out.println("1. ZURUCK");
        scanner.reset();

        Integer input = scanner.nextInt();

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

        Integer input = scanner.nextInt();

        if (input == 1)
            ProduktePage();
        else if (input == 2)
            RemoveProduktPage();
    }

}
