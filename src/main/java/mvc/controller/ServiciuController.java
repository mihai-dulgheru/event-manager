package mvc.controller;

import interfaces.ViewUpdater;
import mvc.model.Serviciu;
import mvc.view.ServiciuView;

public class ServiciuController implements ViewUpdater {
    private final Serviciu model;
    private final ServiciuView view;

    public ServiciuController(Serviciu model, ServiciuView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.print(model);
    }
}
