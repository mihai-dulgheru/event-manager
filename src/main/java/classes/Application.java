package classes;

import database.Database;
import designPatterns.proxy.Client;
import enums.CategorieEveniment;
import enums.TipEveniment;
import util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
        UUID uuid = autentificare();
        System.out.println(uuid);
        /*
        if (uuid != null) {
            TipEveniment tipEveniment = alegeTipEveniment();
            System.out.println(tipEveniment);
            CategorieEveniment categorieEveniment = alegeCategorieEveniment();
            System.out.println(categorieEveniment);
        }
         */
        Database.disconnect();
    }

    private static UUID autentificare() {
        try {
            System.out.println("Introduceți username-ul: ");
            String username = SCANNER.nextLine();
            System.out.println("Introduceți parola: ");
            String parola = SCANNER.nextLine();
            Client client = Client.findByUsername(username);
            if (client == null) {
                System.out.println("Autentificare eșuată!");
                return null;
            }
            if (PasswordUtil.checkPassword(parola, client.getParola())) {
                System.out.println("Autentificare reușită!");
                return client.getId();
            } else {
                System.out.println("Autentificare eșuată!");
                return null;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
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
