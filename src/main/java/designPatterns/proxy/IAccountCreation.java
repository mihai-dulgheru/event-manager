package designPatterns.proxy;

import exceptions.ClientAgeException;

public interface IAccountCreation {
    Client createAccount(String numeClient, String prenumeClient, String cnp, String adresa, String email, String telefon, String username, String parola) throws ClientAgeException;
}
