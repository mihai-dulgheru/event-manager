package model.enums;

public enum Moneda {
    RON, EUR, USD;

    public static String getValues() {
        StringBuilder values = new StringBuilder();
        for (Moneda moneda : Moneda.values()) {
            values.append("'").append(moneda).append("',");
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }
}
