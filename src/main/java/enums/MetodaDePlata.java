package enums;

public enum MetodaDePlata {
    CASH, CARD, TRANSFER_BANCAR;

    public static String getValues() {
        StringBuilder values = new StringBuilder();
        for (MetodaDePlata metodaDePlata : MetodaDePlata.values()) {
            values.append("'").append(metodaDePlata).append("',");
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }
}
