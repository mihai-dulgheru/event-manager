package designPatterns.proxy;

import exceptions.ClientAgeException;
import mvc.model.Client;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IAccountCreation {
    Client createAccount(String numeClient, String prenumeClient, String cnp, String adresa, String email, String telefon, String username, String parola) throws ClientAgeException, NoSuchAlgorithmException, InvalidKeySpecException;
}
