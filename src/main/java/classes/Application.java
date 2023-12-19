package classes;

import database.Database;
import enums.CategorieEveniment;
import enums.TipEveniment;
import mvc.model.Client;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 * Facade
 */
public class Application {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void start() {
        Database.connect();
        Database.populate();
        UUID uuid = autentificare();
        if (uuid != null) {
            TipEveniment tipEveniment = alegeTipEveniment();
            System.out.println(tipEveniment);
            CategorieEveniment categorieEveniment = alegeCategorieEveniment();
            System.out.println(categorieEveniment);
            // TODO: adaugă abstract factory
        }
        Database.disconnect();
    }

    private static UUID autentificare() {
        System.out.println("Introduceți username-ul: ");
        String username = SCANNER.nextLine();
        System.out.println("Introduceți parola: ");
        String parola = SCANNER.nextLine();
        Client client = Client.findByUsername(username);
        if (client == null) {
            System.out.println("Autentificare eșuată!");
            return null;
        }
        if (client.getParola().equals(parola)) {
            System.out.println("Autentificare reușită!");
            return client.getId();
        } else {
            System.out.println("Autentificare eșuată!");
            return null;
        }
    }

    private static CategorieEveniment alegeCategorieEveniment() {
        Map<Integer, ? extends Enum<?>> categorieEvenimentMap = CategorieEveniment.getEnumMap();
        for (Map.Entry<Integer, ? extends Enum<?>> entry : categorieEvenimentMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("Alegeți categoria evenimentului: ");
        Integer option;
        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (!categorieEvenimentMap.containsKey(option)) {
                System.out.println("Opțiunea nu există!");
                return alegeCategorieEveniment();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return alegeCategorieEveniment();
        }
        return (CategorieEveniment) categorieEvenimentMap.get(option);
    }

    private static TipEveniment alegeTipEveniment() {
        Map<Integer, ? extends Enum<?>> tipEvenimentMap = TipEveniment.getEnumMap();
        for (Map.Entry<Integer, ? extends Enum<?>> entry : tipEvenimentMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("Alegeți tipul evenimentului: ");
        Integer option;
        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (!tipEvenimentMap.containsKey(option)) {
                System.out.println("Opțiunea nu există!");
                return alegeTipEveniment();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return alegeTipEveniment();
        }
        return (TipEveniment) tipEvenimentMap.get(option);
    }
}
