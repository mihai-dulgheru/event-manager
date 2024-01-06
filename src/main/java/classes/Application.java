package classes;

import abstractClasses.AbstractModel;
import database.Database;
import designPatterns.abstractFactory.*;
import designPatterns.proxy.ClientProxy;
import designPatterns.singleton.CatalogServicii;
import enums.CategorieEveniment;
import enums.MetodaDePlata;
import enums.TipEveniment;
import exceptions.ClientAgeException;
import mvc.controller.*;
import mvc.model.*;
import mvc.view.*;
import util.DateUtil;
import util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.*;

/**
 * Facade
 */
public class Application {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Meniu MENIU = new Meniu();

    public static void start() {
        try {
            Database.connect();

            Integer optiuneDeschidereMeniu = MENIU.deschidereAplicatie();
            Client client = null;
            ClientView clientView = null;
            ClientController clientController = null;
            if (optiuneDeschidereMeniu == 2) {
                client = creareCont();
                optiuneDeschidereMeniu = 1;
            }
            if (optiuneDeschidereMeniu == 1) {
                if (client == null) {
                    client = autentificare();
                }
                clientView = new ClientView();
                clientController = new ClientController(client, clientView);

                Integer optiuneUserAutentificat = MENIU.afiseazaOptiuniUser();
                if (optiuneUserAutentificat == 1) {
                    Contract contract = new Contract(clientController.getIdClient());
                    ContractView contractView = new ContractView();
                    ContractController contractController = new ContractController(contract, contractView);

                    TipEveniment tipEveniment = alegeTipEveniment();
                    CategorieEveniment categorieEveniment = alegeCategorieEveniment();
                    String dataEveniment = alegeDataEveniment();

                    Locatie locatie = alegeLocatie(dataEveniment);
                    LocatieView locatieView = new LocatieView();
                    LocatieController locatieController = new LocatieController(locatie, locatieView);

                    Integer nrParticipanti = alegeNrParticipanti(locatieController.getCapacitateLocatie());

                    Eveniment eveniment = creareEveniment(tipEveniment, categorieEveniment, contractController.getIdContract(), locatieController.getIdLocatie(), dataEveniment, nrParticipanti);
                    EvenimentView evenimentView = new EvenimentView();
                    EvenimentController evenimentController = new EvenimentController(eveniment, evenimentView);

                    evenimentController.getEveniment().insert();

                    Pachet pachet = alegePachet(evenimentController.getEveniment());
                    PachetView pachetView = new PachetView();
                    PachetController pachetController = new PachetController(pachet, pachetView);

                    String dataIncheiere = DateUtil.today();
                    String observatii = adaugaObservatii();
                    MetodaDePlata metodaDePlata = alegeMetodaDePlata();

                    contractController.updateContract(dataIncheiere, pachetController.getPachet(), observatii, metodaDePlata);
                    contractController.updateView();

                    Database.saveAll(clientController.getClient(), contractController.getContract(), evenimentController.getEveniment(), pachetController.getPachet());
                }
                if (optiuneUserAutentificat == 2) {
                    System.out.println("Evenimente: ");
                    assert client != null;
                    vizualizareEvenimenteUser(client.getId());
                }
                if (optiuneUserAutentificat == 3) {
                    assert client != null;
                    schimbareParola(client.getId());
                }
            }

            // TODO: implementează metodele de afișare modele din MVC
            // TODO: fa meniul recursiv + adăugare opțiune de ieșire din meniu
            // TODO: să poată accepta doar y/n

            Database.commit();
        } catch (Exception e) {
            Database.rollback();
            System.out.println("Eroare: " + e.getMessage());
        } finally {
            Database.disconnect();
            SCANNER.close();
        }
    }

    private static Client autentificare() {
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
                return client;
            } else {
                System.out.println("Autentificare eșuată!");
                return null;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static CategorieEveniment alegeCategorieEveniment() {
        System.out.println("Categorii de evenimente disponibile: ");
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
        System.out.println("Tipuri de evenimente disponibile: ");
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

    private static String alegeDataEveniment() {
        System.out.println("Introduceți data evenimentului (yyyy-MM-dd): ");
        String dataEveniment = SCANNER.nextLine();
        if (dataEveniment.isEmpty() || !DateUtil.isValidDate(dataEveniment)) {
            System.out.println("Data evenimentului nu este validă!");
            return alegeDataEveniment();
        }
        return dataEveniment;
    }

    private static Locatie alegeLocatie(String dataEveniment) {
        try {
            List<Locatie> locatii = Locatie.findByDataEveniment(dataEveniment);
            if (locatii.isEmpty()) {
                System.out.println("Nu există locații disponibile pentru data introdusă!");
                return null;
            }
            System.out.println("Locații disponibile: ");
            for (int i = 0; i < locatii.size(); i++) {
                System.out.println(i + 1 + ". " + locatii.get(i).getDenumire() + " (" + locatii.get(i).getCapacitate() + " persoane)");
            }
            System.out.println("Alegeți locația: ");
            int option;
            try {
                option = Integer.parseInt(SCANNER.nextLine());
                if (option < 1 || option >= locatii.size() + 1) {
                    System.out.println("Opțiunea nu există!");
                    return alegeLocatie(dataEveniment);
                }
            } catch (NumberFormatException e) {
                System.out.println("Opțiunea nu este un număr!");
                return alegeLocatie(dataEveniment);
            }
            return locatii.get(option - 1);
        } catch (SQLException e) {
            return null;
        }
    }

    private static Integer alegeNrParticipanti(Integer capacitate) {
        System.out.println("Introduceți numărul de participanți: ");
        int nrParticipanti;
        try {
            nrParticipanti = Integer.parseInt(SCANNER.nextLine());
            if (nrParticipanti < 0) {
                System.out.println("Numărul de participanți nu poate fi negativ!");
                return alegeNrParticipanti(capacitate);
            }
            if (nrParticipanti > capacitate) {
                System.out.println("Numărul de participanți nu poate fi mai mare decât capacitatea locației!");
                return alegeNrParticipanti(capacitate);
            }
        } catch (NumberFormatException e) {
            System.out.println("Numărul de participanți nu este un număr!");
            return alegeNrParticipanti(capacitate);
        }
        return nrParticipanti;
    }

    private static Eveniment creareEveniment(TipEveniment tipEveniment, CategorieEveniment categorieEveniment, UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        EvenimentFactory factory = switch (tipEveniment) {
            case TipEveniment.BOTEZ -> new BotezFactory();
            case TipEveniment.CONCERT -> new ConcertFactory();
            case TipEveniment.EXPOZITIE -> new ExpozitieFactory();
            case TipEveniment.FESTIVAL -> new FestivalFactory();
            case TipEveniment.NUNTA -> new NuntaFactory();
            case TipEveniment.PETRECERE_ABSOLVIRE -> new PetrecereAbsolvireFactory();
        };
        return switch (categorieEveniment) {
            case CategorieEveniment.CU_TEMATICA ->
                    factory.createEvenimentCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
            case CategorieEveniment.FARA_TEMATICA ->
                    factory.createEvenimentFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
        };
    }

    private static Pachet alegePachet(Eveniment eveniment) throws SQLException {
        TipEveniment tipEveniment = eveniment.getTipEveniment();
        CatalogServicii catalogServicii = CatalogServicii.getInstance();
        Pachet.PachetBuilder pachetBuilder = new Pachet.PachetBuilder(eveniment.getId());
        System.out.println("Pachetul dumneavoastră conține următoarele servicii: ");
        List<AbstractModel> serviciiDefault = Serviciu.readServiciiDefault();
        for (int i = 0; i < serviciiDefault.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, ((Serviciu) (serviciiDefault.get(i))).getNumeServiciu());
        }
        System.out.println("Doriți să adăugați un serviciu? (y/n)");
        String answer = SCANNER.nextLine();
        while (answer.equals("y")) {
            System.out.println("Servicii disponibile: ");
            catalogServicii.afiseazaServicii(tipEveniment);
            System.out.println("Alegeți serviciul: ");
            int option;
            try {
                option = Integer.parseInt(SCANNER.nextLine());
                if (option < 1 || option >= catalogServicii.getCatalog().get(tipEveniment).size() + 1) {
                    System.out.println("Opțiunea nu există!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opțiunea nu este un număr!");
                continue;
            }
            pachetBuilder.addServiciu(catalogServicii.getCatalog().get(tipEveniment).get(option - 1));
            System.out.println("Serviciul a fost adăugat cu succes!");
            System.out.println("Doriți să adăugați un serviciu? (y/n)");
            answer = SCANNER.nextLine();
        }
        return pachetBuilder.build();
    }

    private static String adaugaObservatii() {
        System.out.println("Introduceți detalii suplimentare: ");
        return SCANNER.nextLine();
    }

    private static MetodaDePlata alegeMetodaDePlata() {
        System.out.println("Metode de plata disponibile: ");
        Map<Integer, ? extends Enum<?>> metodaDePlataMap = MetodaDePlata.getEnumMap();
        for (Map.Entry<Integer, ? extends Enum<?>> entry : metodaDePlataMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("Alegeți metoda de plata: ");
        Integer option;
        try {
            option = Integer.parseInt(SCANNER.nextLine());
            if (!metodaDePlataMap.containsKey(option)) {
                System.out.println("Opțiunea nu există!");
                return alegeMetodaDePlata();
            }
        } catch (NumberFormatException e) {
            System.out.println("Opțiunea nu este un număr!");
            return alegeMetodaDePlata();
        }
        return (MetodaDePlata) metodaDePlataMap.get(option);
    }

    private static void vizualizareEvenimenteUser(UUID id) {
        if (id == null || id.toString().isEmpty()) {
            System.out.println("Id-ul clientului nu este valid!");
        } else {
            try {
                List<AbstractModel> contracts = new ArrayList<>(Contract.readContracteByIdClient(id));
                int i = 1;

                for (AbstractModel abstractModel : contracts) {
                    Contract contract = (Contract) abstractModel;
                    List<AbstractModel> evenimente = new ArrayList<>();
                    evenimente.add(Eveniment.readEvenimenteByIdContract(contract.getId()));
                    for (AbstractModel abstractModelEveniment : evenimente) {
                        Eveniment eveniment = (Eveniment) abstractModelEveniment;
                        AbstractModel abstractModelLocatie = Locatie.readDenumireLocatie(eveniment.getIdLocatie());
                        Locatie locatie = (Locatie) abstractModelLocatie;
                        System.out.println(i++ + ". " + eveniment.getTipEveniment() + ": " + locatie.getDenumire() + " | " + eveniment.getDataEveniment() + " | " + eveniment.getNrParticipanti() + " participanti");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void schimbareParola(UUID id) {
        // TODO
    }

    private static Client creareCont() {
        ClientProxy proxy = new ClientProxy();
        String numeClient = null;
        String prenumeClient = null;
        String cnp = null;
        String adresa = null;
        String email = null;
        String telefon = null;
        String username = null;
        String parola = null;
        String confirmareParola = null;
        do {
            System.out.println("Introduceți numele: ");
            numeClient = SCANNER.nextLine();
            if (numeClient.length() < 3) {
                System.out.println("Numele trebuie să conțină cel puțin 3 caractere!");
            }
        } while (numeClient.length() < 3);
        do {
            System.out.println("Introduceți prenumele: ");
            prenumeClient = SCANNER.nextLine();
            if (prenumeClient.length() < 3) {
                System.out.println("Prenumele trebuie să conțină cel puțin 3 caractere!");
            }
        } while (prenumeClient.length() < 3);
        do {
            System.out.println("Introduceți CNP-ul: ");
            cnp = SCANNER.nextLine();
            if (cnp.length() != 13) {
                System.out.println("CNP-ul trebuie să conțină 13 caractere!");
            }
        } while (cnp.length() != 13);
        do {
            System.out.println("Introduceți adresa: ");
            adresa = SCANNER.nextLine();
            if (adresa.length() < 3) {
                System.out.println("Adresa trebuie să conțină cel puțin 3 caractere!");
            }
        } while (adresa.length() < 3);
        do {
            System.out.println("Introduceți email-ul: ");
            email = SCANNER.nextLine();
            if (email.length() < 3) {
                System.out.println("Email-ul trebuie să conțină cel puțin 3 caractere!");
            }
            if (!email.contains("@")) {
                System.out.println("Email-ul trebuie să conțină @!");
            }
        } while (email.length() < 3 || !email.contains("@"));
        do {
            System.out.println("Introduceți numărul de telefon: ");
            telefon = SCANNER.nextLine();
            if (telefon.length() != 10) {
                System.out.println("Numărul de telefon trebuie să conțină 10 caractere!");
            }
            if (!telefon.startsWith("07")) {
                System.out.println("Numărul de telefon trebuie să înceapă cu 07!");
            }
        } while (telefon.length() != 10 || !telefon.startsWith("07"));
        do {
            System.out.println("Introduceți username-ul: ");
            username = SCANNER.nextLine();
            if (username.length() < 3) {
                System.out.println("Username-ul trebuie să conțină cel puțin 3 caractere!");
            }
            if (Client.findByUsername(username) != null) {
                System.out.println("Username-ul există deja!");
            }
        } while (username.length() < 3 || Client.findByUsername(username) != null);
        do {
            System.out.println("Introduceți parola: ");
            parola = SCANNER.nextLine();
            if (parola.length() < 8) {
                System.out.println("Parola trebuie să conțină cel puțin 8 caractere!");
            }
        } while (parola.length() < 8);
        do {
            System.out.println("Confirmați parola: ");
            confirmareParola = SCANNER.nextLine();
            if (confirmareParola.length() < 8) {
                System.out.println("Parola trebuie să conțină cel puțin 8 caractere!");
            }
            if (!confirmareParola.equals(parola)) {
                System.out.println("Parola trebuie să fie identică cu parola introdusă anterior!");
            }
        } while (confirmareParola.length() < 8 || !confirmareParola.equals(parola));
        try {
            return proxy.createAccount(numeClient, prenumeClient, cnp, adresa, email, telefon, username, parola);
        } catch (ClientAgeException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
