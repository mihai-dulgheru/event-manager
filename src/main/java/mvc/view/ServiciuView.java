package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Serviciu;

public class ServiciuView implements Printable {
    @Override
    public void print(AbstractModel model) {
        Serviciu serviciu = (Serviciu) model;
        if(serviciu.getDurata()==null){
            System.out.println(serviciu.getNumeServiciu() + " | Serviciu pentru " + serviciu.getTipEveniment() +
                    " | " + serviciu.getCostServiciu() + " RON | Serviciu pentru toata durata evenimentului " + serviciu.getObservatii());
        }
        System.out.println(serviciu.getNumeServiciu() + " | Serviciu pentru " + serviciu.getTipEveniment() +
                " | " + serviciu.getCostServiciu() + " RON | " + serviciu.getDurata() + "h | " + serviciu.getObservatii());
    }
}
