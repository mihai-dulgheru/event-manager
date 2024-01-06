package classes;

import java.util.Scanner;


public class Meniu {

    private static volatile Meniu instance;

    private static final Scanner SCANNER = new Scanner(System.in);

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


        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (option != 1 && option != 2) {
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

        System.out.println("Introduceți optiunea: ");
        System.out.println("1. Creaza eveniment");
        System.out.println("2. Vizualizeaza evenimentele");
        System.out.println("3. Schimba parola");

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

}
