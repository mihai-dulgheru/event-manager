package classes;

import java.util.Scanner;


public class Meniu {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static volatile Meniu instance;

    Meniu() {

    }

    public static Meniu getInstance() {
        if (instance == null) {
            synchronized (Meniu.class) {
                if (instance == null) {
                    instance = new Meniu();
                }
            }
        }
        return instance;
    }

    public Integer deschidereAplicatie() {
        Integer option;

        System.out.println("Bun venit!");
        System.out.println("1. Autentificare");
        System.out.println("2. Creare cont");
        System.out.println("3. Ieșire");

        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (option != 1 && option != 2 && option != 3) {
                System.out.println("Opțiunea nu există!");
                return deschidereAplicatie();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return deschidereAplicatie();
        }

        return option;
    }

    public Integer afiseazaOptiuniUser() {
        Integer option;

        System.out.println("Introduceți opțiunea: ");
        System.out.println("1. Crează eveniment");
        System.out.println("2. Vizualizează evenimentele");
        System.out.println("3. Schimbă parola");
        System.out.println("4. Ieșire");

        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (option != 1 && option != 2 && option != 3 && option != 4) {
                System.out.println("Opțiunea nu există!");
                return deschidereAplicatie();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return deschidereAplicatie();
        }

        return option;
    }

}
