package mvc.controller;

import mvc.model.Client;
import mvc.view.ClientView;

public class ClientController {
    private Client client;
    private ClientView view;

    public ClientController(Client client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientView getView() {
        return view;
    }

    public void setView(ClientView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(client);
    }
}
