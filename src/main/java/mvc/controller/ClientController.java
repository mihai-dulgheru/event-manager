package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Client;
import mvc.view.ClientView;

import java.util.UUID;

public class ClientController implements ViewUpdater {
    private final Client model;
    private final ClientView view;

    public ClientController(Client model, ClientView view) {
        this.model = model;
        this.view = view;
    }

    public UUID getIdClient() {
        return this.model.getId();
    }

    public void updateView() {
        view.print(model);
    }

    public Client getClient() {
        return this.model;
    }
}
