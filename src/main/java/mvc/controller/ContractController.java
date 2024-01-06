package mvc.controller;

import enums.MetodaDePlata;
import enums.Moneda;
import interfaces.ViewUpdater;
import mvc.model.Contract;
import mvc.model.Pachet;
import mvc.model.Serviciu;
import mvc.view.ContractView;

import java.util.UUID;

public class ContractController implements ViewUpdater {
    private final Contract model;
    private final ContractView view;

    public ContractController(Contract model, ContractView view) {
        this.model = model;
        this.view = view;
    }

    public UUID getIdContract() {
        return this.model.getId();
    }

    public void updateView() {
        view.print(model);
    }

    public void updateContract(String dataIncheiere, Pachet pachet, String observatii, MetodaDePlata metodaDePlata) {
        model.setDataIncheiere(dataIncheiere);
        double costTotal = 0;
        for (Serviciu serviciu : pachet.getServicii()) {
            costTotal += serviciu.getCostServiciu();
        }
        model.setCostTotal(costTotal);
        model.setMoneda(Moneda.RON);
        model.setObservatii(observatii);
        model.setMetodaDePlata(metodaDePlata);
    }

    public Contract getContract() {
        return this.model;
    }
}
