package enums;

import interfaces.EnumOperations;

import java.util.Map;

public enum CategorieEveniment implements EnumOperations {
    CU_TEMATICA, FARA_TEMATICA;

    public static String getValues() {
        return EnumOperations.getDefaultValues(CategorieEveniment.values());
    }

    public static Map<Integer, ? extends Enum<?>> getEnumMap() {
        return EnumOperations.getDefaultEnumMap(CategorieEveniment.values());
    }
}
