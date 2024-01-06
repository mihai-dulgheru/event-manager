package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Locatie;

public class LocatieView implements Printable {
    @Override
    public void print(AbstractModel model) {
        Locatie locatie = (Locatie) model;
        System.out.println(locatie.getDenumire() + " | " + locatie.getCapacitate() + " persoane | " + locatie.getCost() + " RON");
    }
}
