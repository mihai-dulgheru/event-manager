package enums;

import java.util.Map;
import java.util.TreeMap;

public enum CategorieEveniment {
    CU_TEMATICA, FARA_TEMATICA;

    public static String getValues() {
        StringBuilder values = new StringBuilder();
        for (CategorieEveniment categorieEveniment : CategorieEveniment.values()) {
            values.append("'").append(categorieEveniment).append("',");
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }

    public static Map<Integer, CategorieEveniment> getCategorieEvenimentMap() {
        Map<Integer, CategorieEveniment> categorieEvenimentMap = new TreeMap<>();
        for (int i = 0; i < CategorieEveniment.values().length; i++) {
            categorieEvenimentMap.put(i + 1, CategorieEveniment.values()[i]);
        }
        return categorieEvenimentMap;
    }
}
