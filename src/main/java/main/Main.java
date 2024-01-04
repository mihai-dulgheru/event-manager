package main;

import database.Database;
import designPatterns.singleton.CatalogServicii;
import enums.TipEveniment;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database.connect();
//        Database.populate();
//        Application.start();
        CatalogServicii catalogServicii = CatalogServicii.getInstance();
        List<TipEveniment> tipEvenimentList = catalogServicii.getTipEvenimentList();
        for (TipEveniment tipEveniment : tipEvenimentList) {
            System.out.println(tipEveniment);
            catalogServicii.afiseazaServicii(tipEveniment);
        }
    }
}