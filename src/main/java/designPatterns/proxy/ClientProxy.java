package designPatterns.proxy;

import exceptions.ClientAgeException;
import mvc.model.Client;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.time.LocalDateTime;

public class ClientProxy implements IAccountCreation {
    private static Client client;

    public ClientProxy() {
        client = new Client();
    }

    private static LocalDateTime getDateOfBirth(String cnp) {
        int structureYear = Integer.parseInt(cnp.substring(0, 1));
        String century = switch (structureYear) {
            case 1, 2 -> "19";
            case 3, 4 -> "18";
            case 5, 6 -> "20";
            default -> "";
        };
        String year = cnp.substring(1, 3);
        int month = Integer.parseInt(cnp.substring(3, 5));
        int dayOfMonth = Integer.parseInt(cnp.substring(5, 7));
        return LocalDateTime.of(Integer.parseInt(century + year), month, dayOfMonth, 0, 0);
    }

    private Boolean isClientMajor(String cnp) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateOfBirth = getDateOfBirth(cnp);
        return Duration.between(dateOfBirth, now).toDays() > Duration.between(now.minusYears(18), now).toDays();
    }

    @Override
    public Client createAccount(String numeClient, String prenumeClient, String cnp, String adresa, String email, String telefon, String username, String parola) throws ClientAgeException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (!isClientMajor(cnp)) {
            throw new ClientAgeException("Clientul nu are vârsta necesară pentru a crea un cont!");
        }
        return client.createAccount(numeClient, prenumeClient, cnp, adresa, email, telefon, username, parola);
    }
}
