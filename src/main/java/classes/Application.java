package classes;

import database.Database;
import enums.TipEveniment;
import mvc.model.Client;
import mvc.model.Pachet;

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
            TipEveniment tipEveniment = alegeEveniment();
            Pachet pachet = alegePachet(tipEveniment);
        }
        Database.disconnect();
    }

    private static UUID autentificare() {
        System.out.println("Introduceți username-ul: ");
        String username = SCANNER.nextLine();
        System.out.println("Introduceți parola: ");
        String parola = SCANNER.nextLine();
        Client client = Client.findByUsername(username);
        if (client.getParola().equals(parola)) {
            System.out.println("Autentificare reușită!");
            return client.getId();
        } else {
            System.out.println("Autentificare eșuată!");
            return null;
        }
    }

    private static TipEveniment alegeEveniment() {
        Map<Integer, TipEveniment> tipEvenimentMap = TipEveniment.getTipEvenimentMap();
        for (Map.Entry<Integer, TipEveniment> entry : tipEvenimentMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("Alegeți evenimentul: ");
        Integer option;
        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (!tipEvenimentMap.containsKey(option)) {
                System.out.println("Opțiunea nu există!");
                return alegeEveniment();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return alegeEveniment();
        }
        return tipEvenimentMap.get(option);
    }

    private static Pachet alegePachet(TipEveniment tipEveniment) {
        System.out.println("Alegeți pachetul: ");

        return null;
    }
}
