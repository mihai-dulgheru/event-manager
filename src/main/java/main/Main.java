package main;

import classes.Application;
import database.Database;
import designPatterns.builder.Computer;
import mvc.model.Client;
import designPatterns.proxy.ClientProxy;
import designPatterns.proxy.IAccountCreation;
import exceptions.ClientAgeException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) {
        Application.start();
        /*
        try {
            Database.connect();
            Database.populate();

            List<AbstractModel> contracte = Contract.readMany();
            Contract contract = (Contract) contracte.get(0);

            List<AbstractModel> locatii = Locatie.readMany();
            Locatie locatie = (Locatie) locatii.get(0);

            EvenimentFactory factory;
            factory = new BotezFactory();
            factory = new ConcertFactory();
            factory = new ExpozitieFactory();
            factory = new FestivalFactory();
            factory = new NuntaFactory();
            factory = new PetrecereAbsolvireFactory();

            EvenimentCuTematica evenimentCuTematica = factory.createEvenimentCuTematica(contract.getId(), locatie.getId(), DateUtil.today(), 100);
            EvenimentFaraTematica evenimentFaraTematica = factory.createEvenimentFaraTematica(contract.getId(), locatie.getId(), DateUtil.today(), 100);

            evenimentCuTematica.tiparesteInvitatie();
            evenimentFaraTematica.tiparesteInvitatie();

            IAccountCreation proxy = new ClientProxy();
            Client client = proxy.createAccount("Nume Client 1", "Prenume Client 1", "5000101409887", "Adresa Client 1", "Email Client 1", "Telefon Client 1", "client1", "parola1");
            System.out.println(client);
        } catch (SQLException | ClientAgeException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect();
        }
         */
    }
}