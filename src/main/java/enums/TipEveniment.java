package enums;

import java.util.Map;
import java.util.TreeMap;

public enum TipEveniment {
    BOTEZ, CONCERT, EXPOZITIE, FESTIVAL, NUNTA, PETRECERE_ABSOLVIRE;

    public static String getValues() {
        StringBuilder values = new StringBuilder();
        for (TipEveniment tipEveniment : TipEveniment.values()) {
            values.append("'").append(tipEveniment).append("',");
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }

    public static Map<Integer, TipEveniment> getTipEvenimentMap() {
        Map<Integer, TipEveniment> tipEvenimentMap = new TreeMap<>();
        for (int i = 0; i < TipEveniment.values().length; i++) {
            tipEvenimentMap.put(i + 1, TipEveniment.values()[i]);
        }
        return tipEvenimentMap;
    }
}
