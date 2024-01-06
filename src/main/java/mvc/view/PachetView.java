package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Pachet;
import mvc.model.Serviciu;

public class PachetView implements Printable {
    @Override
    public void print(AbstractModel model) {
        Pachet pachet = (Pachet) model;
        StringBuilder string = new StringBuilder();
        string.append(pachet.getNumePachet());
        string.append(" | Pachetul conține serviciile: ");
        for (Serviciu serviciu : pachet.getServicii()) {
            if (serviciu.equals(pachet.getServicii().get(pachet.getServicii().size() - 1))) {
                string.append(" ");
                string.append(serviciu.getNumeServiciu());
            } else {
                string.append(" ");
                string.append(serviciu.getNumeServiciu());
                string.append(",");
            }
        }
        string.append(" | ");
        string.append(pachet.getDetaliiPachet());
        System.out.println(string);
    }
}
