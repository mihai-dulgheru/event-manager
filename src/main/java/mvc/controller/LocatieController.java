package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Locatie;
import mvc.view.LocatieView;

import java.util.UUID;

public class LocatieController implements ViewUpdater {
    private final Locatie model;
    private final LocatieView view;

    public LocatieController(Locatie model, LocatieView view) {
        this.model = model;
        this.view = view;
    }

    public UUID getIdLocatie() {
        return this.model.getId();
    }

    public Integer getCapacitateLocatie() {
        return this.model.getCapacitate();
    }

    public void updateView() {
        view.print(model);
    }
}
