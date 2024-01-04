package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Eveniment;
import mvc.view.EvenimentView;

import java.util.UUID;

public class EvenimentController implements ViewUpdater {
    private final Eveniment model;
    private final EvenimentView view;

    public EvenimentController(Eveniment model, EvenimentView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.print(model);
    }

    public UUID getIdEveniment() {
        return this.model.getId();
    }

    public Eveniment getEveniment() {
        return this.model;
    }
}
