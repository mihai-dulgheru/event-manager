package enums;

import interfaces.EnumOperations;

import java.util.Map;

public enum TipEveniment implements EnumOperations {
    BOTEZ, CONCERT, EXPOZITIE, FESTIVAL, NUNTA, PETRECERE_ABSOLVIRE;

    public static String getValues() {
        return EnumOperations.getDefaultValues(TipEveniment.values());
    }

    public static Map<Integer, ? extends Enum<?>> getEnumMap() {
        return EnumOperations.getDefaultEnumMap(TipEveniment.values());
    }
}
