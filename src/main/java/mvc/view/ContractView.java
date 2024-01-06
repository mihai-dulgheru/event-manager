package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Contract;

public class ContractView implements Printable {
    @Override
    public void print(AbstractModel model) {
        Contract contract = (Contract) model;
        System.out.println(contract.getDataIncheiere() + " | " + contract.getCostTotal() + " | " + contract.getMoneda() + " | " + contract.getMetodaDePlata() + " | " + contract.getObservatii());
    }
}
