package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Locatie;
import mvc.view.LocatieView;

public class LocatieController implements ViewUpdater {
    private Locatie locatie;
    private LocatieView view;

    public LocatieController(Locatie locatie, LocatieView view) {
        this.locatie = locatie;
        this.view = view;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public LocatieView getView() {
        return view;
    }

    public void setView(LocatieView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(locatie);
    }
}
