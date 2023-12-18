package enums;

import interfaces.EnumOperations;

import java.util.Map;

public enum Moneda implements EnumOperations {
    RON, EUR, USD;

    public static String getValues() {
        return EnumOperations.getDefaultValues(Moneda.values());
    }

    public static Map<Integer, ? extends Enum<?>> getEnumMap() {
        return EnumOperations.getDefaultEnumMap(Moneda.values());
    }
}
