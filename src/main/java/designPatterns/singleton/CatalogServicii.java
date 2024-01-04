package designPatterns.singleton;

import abstractClasses.AbstractModel;
import enums.TipEveniment;
import mvc.model.Serviciu;

import java.sql.SQLException;
import java.util.*;

public class CatalogServicii {
    private static volatile CatalogServicii instance;
    private final Map<TipEveniment, List<Serviciu>> catalog;

    private CatalogServicii() {
        try {
            List<AbstractModel> list = Serviciu.readMany();
            this.catalog = new HashMap<>();
            for (AbstractModel abstractModel : list) {
                Serviciu serviciu = (Serviciu) abstractModel;
                if (serviciu.getTipEveniment() == null || serviciu.getTipEveniment().isEmpty() || serviciu.getTipEveniment().equals("DEFAULT")) {
                    continue;
                }
                TipEveniment tipEveniment = TipEveniment.valueOf(serviciu.getTipEveniment());
                if (this.catalog.containsKey(tipEveniment)) {
                    this.catalog.get(tipEveniment).add(serviciu);
                } else {
                    List<Serviciu> servicii = new ArrayList<>();
                    servicii.add(serviciu);
                    this.catalog.put(tipEveniment, servicii);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public List<TipEveniment> getTipEvenimentList() {
        return new ArrayList<>(this.catalog.keySet());
    }
}
