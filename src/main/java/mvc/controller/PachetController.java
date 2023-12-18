package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Pachet;
import mvc.view.PachetView;

public class PachetController implements ViewUpdater {
    private Pachet pachet;
    private PachetView view;

    public PachetController(Pachet pachet, PachetView view) {
        this.pachet = pachet;
        this.view = view;
    }

    public Pachet getPachet() {
        return pachet;
    }

    public void setPachet(Pachet pachet) {
        this.pachet = pachet;
    }

    public PachetView getView() {
        return view;
    }

    public void setView(PachetView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(pachet);
    }
}
