package mvc.controller;

import mvc.model.Serviciu;
import mvc.view.ServiciuView;

public class ServiciuController {
    private Serviciu serviciu;
    private ServiciuView view;

    public ServiciuController(Serviciu serviciu, ServiciuView view) {
        this.serviciu = serviciu;
        this.view = view;
    }

    public Serviciu getServiciu() {
        return serviciu;
    }

    public void setServiciu(Serviciu serviciu) {
        this.serviciu = serviciu;
    }

    public ServiciuView getView() {
        return view;
    }

    public void setView(ServiciuView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(serviciu);
    }
}
