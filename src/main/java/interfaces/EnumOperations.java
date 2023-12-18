package interfaces;

import java.util.Map;
import java.util.TreeMap;

public interface EnumOperations {
    static String getValues() {
        return null;
    }

    static Map<Integer, ? extends Enum<?>> getEnumMap() {
        return null;
    }

    static String getDefaultValues(Enum<?>[] values) {
        StringBuilder result = new StringBuilder();
        for (Enum<?> enumValue : values) {
            result.append("'").append(enumValue).append("',");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    static Map<Integer, ? extends Enum<?>> getDefaultEnumMap(Enum<?>[] values) {
        Map<Integer, Enum<?>> enumMap = new TreeMap<>();
        for (int i = 0; i < values.length; i++) {
            enumMap.put(i + 1, values[i]);
        }
        return enumMap;
    }
}
