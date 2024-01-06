package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Client;

public class ClientView implements Printable {
    @Override
    public void print(AbstractModel model) {
        Client client = (Client) model;
        System.out.println(client.getNumeClient() + " " + client.getPrenumeClient() + " | " + client.getCnp() +
                " | " + client.getAdresa() + " | " + client.getTelefon() + " | " + client.getEmail() + " | " + client.getUsername());
    }
}
