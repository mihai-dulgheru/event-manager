package enums;

import interfaces.EnumOperations;

import java.util.Map;

public enum MetodaDePlata implements EnumOperations {
    CASH, CARD, TRANSFER_BANCAR;

    public static String getValues() {
        return EnumOperations.getDefaultValues(MetodaDePlata.values());
    }

    public static Map<Integer, ? extends Enum<?>> getEnumMap() {
        return EnumOperations.getDefaultEnumMap(MetodaDePlata.values());
    }
}
