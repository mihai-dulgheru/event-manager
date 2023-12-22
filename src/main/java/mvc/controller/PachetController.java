package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Pachet;
import mvc.view.PachetView;

public class PachetController implements ViewUpdater {
    private final Pachet model;
    private final PachetView view;

    public PachetController(Pachet model, PachetView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.print(model);
    }
}
