package designPatterns.singleton;

import enums.TipEveniment;
import mvc.model.Serviciu;

import java.util.*;

public class CatalogServicii {
    private static volatile CatalogServicii instance;
    private final Map<TipEveniment, List<Serviciu>> catalog;

    private CatalogServicii() {
        // TODO: citește valorile din baza de date populată în metoda `Database.populate()`
        this.catalog = HashMap.newHashMap(1);
        Serviciu serviciu1 = new Serviciu("Serviciu 1", 100.0f, 1.5f, "Observații", TipEveniment.BOTEZ.toString());
        List<Serviciu> servicii = new ArrayList<>();
        servicii.add(serviciu1);
        catalog.put(TipEveniment.BOTEZ, servicii);
    }

    public static CatalogServicii getInstance() {
        if (instance == null) {
            synchronized (CatalogServicii.class) {
                if (instance == null) {
                    instance = new CatalogServicii();
                }
            }
        }
        return instance;
    }

    public Map<TipEveniment, List<Serviciu>> getCatalog() {
        return catalog;
    }

    public void afiseazaServicii(TipEveniment tipEveniment) {
        List<Serviciu> servicii = this.catalog.get(tipEveniment);
        for (int i = 0; i < servicii.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, servicii.get(i).print());
        }
    }

    public List<Serviciu> getServicii(TipEveniment tipEveniment, String servicii, UUID idPachet) {
        List<Serviciu> serviciiList = new ArrayList<>();
        String[] serviciiIds = servicii.split(",");
        for (String serviciuId : serviciiIds) {
            serviciiList.add(this.catalog.get(tipEveniment).get(Integer.parseInt(serviciuId.trim()) - 1));
        }
        return serviciiList;
    }
}
