package model.enums;

public enum TipEveniment {
    BOTEZ, CONCERT, CONFERINTA, CUNUNIE, EXPOZITIE, FESTIVAL, INMORMANTARE, NUNTA, PETRECERE_ABSOLVIRE, ALTELE;

    public static String getValues() {
        StringBuilder values = new StringBuilder();
        for (TipEveniment tipEveniment : TipEveniment.values()) {
            values.append("'").append(tipEveniment).append("',");
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }
}
