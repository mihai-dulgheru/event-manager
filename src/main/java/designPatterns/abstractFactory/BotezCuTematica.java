package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class BotezCuTematica extends EvenimentCuTematica {
    public BotezCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.BOTEZ, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        String string = "E momentul să împărtășim o veste minunată cu tine! Te invităm cu drag la un eveniment special - Botezul cu tematică pe care îl organizăm pentru micuțul nostru îngeraș.\n\n"
                + "Detalii eveniment:\n" + "- Dată: " + this.getDataEveniment() + "\n" + "- Locație: "
                + this.getNumeLocatie() + "\n" + "- Tematică: " + this.getTematica() + "\n\n"
                + "Tematica botezului adaugă o notă specială acestei zile, creând un cadru magic și memorabil.\n\n"
                + "Te rugăm să confirmi prezența până la data de " + this.getDataLimitaConfirmare() + ".\n\n"
                + "Suntem nerăbdători să împărtășim această zi cu tine și să creăm amintiri frumoase împreună!\n\n"
                + "Cu drag,\n" + this.getNumeClient();
        System.out.println(string);
    }
}
