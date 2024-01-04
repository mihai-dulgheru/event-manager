package database;

import designPatterns.proxy.ClientProxy;
import designPatterns.proxy.IAccountCreation;
import enums.MetodaDePlata;
import enums.Moneda;
import enums.TipEveniment;
import exceptions.ClientAgeException;
import mvc.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    public static Connection connection;
    public static Statement statement;

    private Database() {
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populate() {
        try {
            Client[] clients = getClients();

            for (Client client : clients) {
                client.insert();
            }

            Contract[] contracts = {
                    new Contract(clients[0].getId(), 9000d, Moneda.RON, "Planificare eveniment botez", MetodaDePlata.CARD),
                    new Contract(clients[1].getId(), 80000d, Moneda.EUR, "Producție și management concert", MetodaDePlata.TRANSFER_BANCAR),
                    new Contract(clients[2].getId(), 12000d, Moneda.RON, "Organizare stand expoziție", MetodaDePlata.CARD),
                    new Contract(clients[3].getId(), 50000d, Moneda.EUR, "Planificare festival complet", MetodaDePlata.CASH),
                    new Contract(clients[4].getId(), 40000d, Moneda.EUR, "Planificare nuntă integrală", MetodaDePlata.TRANSFER_BANCAR),};

            for (Contract contract : contracts) {
                contract.insert();
            }

            Locatie[] locations = {
                    new Locatie("Gradina Floreasca", 140, 7000),
                    new Locatie("Arenele Romane", 5000, 250000),
                    new Locatie("Galeria Galateca", 140, 7000),
                    new Locatie("Romexpo", 8000, 400000),
                    new Locatie("Palatul Știrbey", 200, 10000),};

            for (Locatie locatie : locations) {
                locatie.insert();
            }

            Eveniment[] events = {
                    new Eveniment(contracts[0].getId(), locations[0].getId(), TipEveniment.BOTEZ, "2023-12-17", 80),
                    new Eveniment(contracts[1].getId(), locations[1].getId(), TipEveniment.CONCERT, "2023-12-18", 2000),
                    new Eveniment(contracts[2].getId(), locations[2].getId(), TipEveniment.EXPOZITIE, "2023-12-19", 130),
                    new Eveniment(contracts[3].getId(), locations[3].getId(), TipEveniment.FESTIVAL, "2023-12-20", 4000),
                    new Eveniment(contracts[4].getId(), locations[4].getId(), TipEveniment.NUNTA, "2023-12-21", 200),
            };

            for (Eveniment eveniment : events) {
                eveniment.insert();
            }

            Serviciu[] servicii = {
                    new Serviciu("Curățenie", 2000f, null, "Calitate superioară în serviciile de curățenie", "DEFAULT"),
                    new Serviciu("Servicii de siguranță și prim ajutor", 3000f, null, "Asigurarea unui personal de siguranță și prim ajutor pentru a gestiona situații de urgență.", "DEFAULT"),
                    new Serviciu("Decorare Biserică și Locație", 2000f, 2f, "Aranjamente florale și decor pentru biserică și locul de petrecere", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Catering Botez", 9000f, null, "Meniu special pentru petrecerea de botez, inclusiv băuturi și deserturi", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Fotografie și Videografie Botez", 7000f, null, "Servicii profesionale de capturare a momentelor speciale ale botezului", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Muzică și Distracție Botez", 3800f, 2f, "DJ sau muzică live pentru a menține atmosfera veselă la petrecere", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Transport Oaspeți", 6000f, 2f, "Serviciu de transport pentru invitați între locul botezului și locul petrecerii", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Animatori pentru Copii", 3500f, 3f, "Servicii de animatori pentru a distra copiii în timpul evenimentului", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Tort și Dulciuri Personalizate", 2000f, null, "Tort de botez personalizat și dulciuri tematice pentru petrecere", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Cadouri pentru Invitați", 1200f, null, "Pachete de cadouri pentru invitați, inclusiv mărturii personalizate și suveniruri", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Servicii Religioase", 800f, 2f, "Asistență în organizarea serviciilor religioase pentru momentul botezului", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Logistică și Planificare Eveniment", 7000f, null, "Coordonarea logistică a evenimentului, gestionarea spațiului, programarea artiștilor și a echipamentelor.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Sunet și Iluminare Profesională", 40000f, null, "Furnizarea de echipamente de sunet și iluminare de înaltă calitate pentru o experiență audiovizuală excelentă.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Servicii de Catering și Băuturi", 14000f, null, "Oferirea de opțiuni de catering pentru a satisface nevoile alimentare ale participanților la concert.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Securitate și Echipă de Gestionare a Mulțimii", 18000f, null, "Asigurarea măsurilor de securitate și personal pentru a gestiona mulțimea și a menține siguranța evenimentului.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Bilete și Gestiunea Accesului", 9000f, null, "Sistem eficient de vânzare a biletelor online sau offline și gestionarea accesului la eveniment.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Merchandising și Standuri de Vânzare", 6000f, 6f, "Oferirea de spații pentru standuri de vânzare de merchandise ale artiștilor sau pentru produse tematice legate de concert.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Personal de Coordonare a Evenimentului", 10000f, null, "Echipă dedicată pentru coordonarea evenimentului, gestionarea backstage-ului și a detaliilor logistice.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Toalete Mobile și Facilități Sanitare", 10000f, null, "Asigurarea de facilități sanitare adecvate pentru participanți.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Promovare și Publicitate Eveniment", 4000f, 8f, "Servicii de marketing și publicitate pentru a atrage un public mai mare la concert.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Transport și Parcare", 10000f, null, "Asigurarea de opțiuni de transport și facilități de parcare pentru participanți.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Decor și Design Scenă", 7800f, 4f, "Crearea unui decor de scenă captivant și iluminare adecvată pentru atmosfera concertului.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Servicii de Vizualizare și Proiecții", 20000f, null, "Proiecții vizuale sau servicii de vizualizare pentru a îmbogăți experiența concertului.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Colectare Selectivă și Gestionare Deșeuri", 7000f, null, "Implementarea unui sistem de colectare selectivă a deșeurilor și gestionare ecologică a acestora.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Facilități pentru Persoane cu Dizabilități", 8500f, null, "Asigurarea de facilități și accesibilitate pentru persoanele cu dizabilități.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Camere de Odihnă pentru Artiști", 9000f, null, "Asigurarea spațiilor confortabile de odihnă și facilități pentru artiști.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Promovare în Social Media", 8000f, 9f, "Gestionarea campaniilor de promovare în social media pentru a atrage audiența.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Servicii de Streaming Live", 13000f, null, "Transmiterea live a concertului pentru fanii care nu pot participa fizic.", TipEveniment.CONCERT.toString()),
                    new Serviciu("Design Expozițional și Standuri", 9000f, 4f, "Crearea unui design atractiv pentru expoziție și standuri personalizate pentru expozanți.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Gestionarea Spațiului Expozițional", 15000f, null, "Coordonarea și asignarea spațiilor expoziționale pentru expozanți și participanți.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Iluminare și Echipamente Audiovizuale", 8000f, null, "Furnizarea de iluminare adecvată și echipamente audiovizuale pentru a evidenția exponatele și a crea o atmosferă plăcută.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Asistență pentru Expozanți", 9000f, null, "Oferirea de asistență pentru expozanți în procesul de instalare și desființare a standurilor.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Marketing și Publicitate Expozițională", 9000f, 7f, "Promovarea expoziției prin campanii de marketing și publicitate pentru a atrage un public mai larg.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Ghiduri și Tururi Expoziționale", 6000f, 3f, "Asigurarea de ghizi și tururi pentru a oferi informații detaliate despre exponate și artiști.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Securitate și Monitorizare Expozițională", 7000f, null, "Asigurarea măsurilor de securitate pentru a proteja exponatele și a monitoriza evenimentul.", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Decor și Design Nunta", 12000f, null, "Crearea unui decor tematic și design personalizat pentru locul de desfășurare a nunții.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Muzică Live sau DJ", 7000f, 1f, "Asigurarea serviciilor muzicale pentru ceremonie și petrecere, fie prin muzică live sau DJ.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Catering și Tort Nunta", 20000f, null, "Oferirea de servicii de catering pentru masă și un tort personalizat pentru ceremonia de nuntă.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Fotografie și Videografie Nunta", 18000f, null, "Capturarea momentelor speciale ale nunții prin fotografii și videoclipuri profesionale.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Transport pentru Mire și Mireasă", 1000f, 3f, "Asigurarea de transport elegant și confortabil pentru mire și mireasă.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Invitații și Papetărie Personalizată", 5000f, 2f, "Design și tipărire de invitații personalizate și alte elemente de papetărie pentru nuntă.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Animatori sau Distracții pentru Copii", 3000f, 2f, "Organizarea de activități distractive pentru copii pentru a se bucura în timpul evenimentului.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Servicii Religioase sau Oficierea Nuntii", 2000f, 2f, "Asigurarea de servicii religioase sau a unui oficiant pentru a oficia ceremonia de nuntă.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Servicii de Beauty și Coafură", 6000f, 5f, "Oferirea de servicii profesionale de înfrumusețare și coafură pentru mireasă și domnișoarele de onoare.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Bijuterii și Accesorii Nunta", 5000f, null, "Oferirea de bijuterii și accesorii elegante pentru mireasă și domnișoarele de onoare.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Servicii de Dans și Coregrafie", 1600f, 10f, "Organizarea de lecții de dans și coregrafie pentru mire și mireasă.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Servicii de Proiecții și Lumini Ambientale", 3200f, null, "Furnizarea de proiecții și iluminare ambientală pentru a crea o atmosferă de vis.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Stilist pentru Mire și Domnișoare de Onoare", 4000f, 6f, "Consultanță în alegerea ținutelor și stilizarea mirelui și a domnișoarelor de onoare.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Servicii de Artificii sau Lumini și Sunet Spectaculos", 4000f, 2f, "Organizarea unui spectacol impresionant de artificii sau un show de lumini și sunet.", TipEveniment.NUNTA.toString()),
                    new Serviciu("Decor și Design Eveniment", 4000f, 4f, "Crearea unui decor festiv și design tematic pentru evenimentul de absolvire.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Muzică și DJ pentru Petrecere", 3800f, 2f, "Asigurarea de servicii muzicale și DJ pentru a menține atmosfera veselă pe parcursul petrecerii de absolvire.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Servicii de Catering și Băuturi", 10000f, null, "Oferirea de opțiuni variate de catering și băuturi pentru a satisface gusturile invitaților.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Fotografie și Fotobooth", 8000f, null, "Capturarea momentelor speciale prin servicii profesionale de fotografie și fotobooth.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Animatori sau Distracții Speciale", 100f, 2f, "Organizarea de distracții speciale sau animatori pentru a aduce o notă de amuzament la petrecerea de absolvire.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Servicii de Proiecții și Lumini Ambiente", 4000f, null, "Utilizarea de proiecții și iluminare ambientală pentru a crea o atmosferă festivă și modernă.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Securitate și Gestionare a Mulțimii", 4000f, null, "Asigurarea măsurilor de securitate și personal pentru a gestiona mulțimea și a menține ordinea.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Servicii de Barman și Cocktail-uri", 5000f, null, "Asigurarea de băuturi speciale și servicii de barman pentru a oferi o experiență deosebită la capitolul băuturi.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Servicii de Dans și Coregrafie", 7000f, 6f, "Organizarea de lecții de dans și coregrafie pentru a aduce energie pe ringul de dans.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Servicii de Proiecții Video și Montaj Foto", 4000f, null, "Prezentarea de montaje video și fotografii pentru a reflecta momentele memorabile din perioada de școală.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Spectacol de Artificii sau Lumină și Sunet", 3500f, 2f, "Organizarea unui spectacol impresionant de artificii sau un show de lumină și sunet pentru a marca absolvirea.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),
                    new Serviciu("Cadouri și Amintiri pentru Absolvenți", 3000f, 3f, "Oferirea de cadouri și suveniruri personalizate pentru absolvenți.", TipEveniment.PETRECERE_ABSOLVIRE.toString()),};


            for (Serviciu serviciu : servicii) {
                serviciu.insert();
            }

            Pachet[] packets = {
                    new Pachet.PachetBuilder(events[0].getId()).addServiciu(servicii[2]).build(),
                    new Pachet.PachetBuilder(events[1].getId()).addServiciu(servicii[3]).build(),
                    new Pachet.PachetBuilder(events[2].getId()).addServiciu(servicii[4]).build(),
                    new Pachet.PachetBuilder(events[3].getId()).addServiciu(servicii[5]).build(),
                    new Pachet.PachetBuilder(events[4].getId()).addServiciu(servicii[6]).build(),};

            for (Pachet pachet : packets) {
                pachet.insert();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Client[] getClients() throws ClientAgeException, NoSuchAlgorithmException, InvalidKeySpecException {
        IAccountCreation proxy = new ClientProxy();
        return new Client[]{
                proxy.createAccount("Popescu", "Ana", "5010101408138", "Strada Florilor, Nr. 12, București", "ana.popescu@email.com", "+40721234567", "ana_pop", "parola123"),
                proxy.createAccount("Georgescu", "Mihai-Leonard", "5010101408137", "Bulevardul Libertății, Nr. 45, Cluj-Napoca", "mihai.georgescu@email.com", "+40731234567", "mihai_g", "parola456"),
                proxy.createAccount("Ionescu", "Elena", "5010101408136", "Aleea Albastră, Nr. 8, Timișoara", "elena.ionescu@email.com", "+40741234567", "elena_i", "parola789"),
                proxy.createAccount("Vasilescu", "Adrian-Marian", "5010101408135", "Bulevardul Unirii, Nr. 21, Iași", "adrian.vasilescu@email.com", "+40751234567", "adrian_v", "parolaABC"),
                proxy.createAccount("Radu", "Cristina-Andreea", "5010101408134", "Strada Soarelui, Nr. 17, Galați", "cristina.radu@email.com", "+40761234567", "cristina_r", "parolaDEF"),};
    }
}
