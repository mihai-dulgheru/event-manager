package mvc.controller;

import mvc.model.Eveniment;
import mvc.view.EvenimentView;

public class EvenimentController {
    private Eveniment eveniment;
    private EvenimentView view;

    public EvenimentController(Eveniment eveniment, EvenimentView view) {
        this.eveniment = eveniment;
        this.view = view;
    }

    public Eveniment getEveniment() {
        return eveniment;
    }

    public void setEveniment(Eveniment eveniment) {
        this.eveniment = eveniment;
    }

    public EvenimentView getView() {
        return view;
    }

    public void setView(EvenimentView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(eveniment);
    }
}
