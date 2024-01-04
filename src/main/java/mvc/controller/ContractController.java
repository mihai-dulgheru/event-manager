package mvc.controller;

import enums.MetodaDePlata;
import interfaces.ViewUpdater;
import mvc.model.Contract;
import mvc.model.Pachet;
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
    }
}
