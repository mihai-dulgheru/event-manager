package mvc.controller;

import mvc.model.Contract;
import mvc.view.ContractView;

public class ContractController {
    private Contract contract;
    private ContractView view;

    public ContractController(Contract contract, ContractView view) {
        this.contract = contract;
        this.view = view;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractView getView() {
        return view;
    }

    public void setView(ContractView view) {
        this.view = view;
    }

    public void updateView() {
        view.print(contract);
    }
}
