package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class BotezFaraTematica extends EvenimentFaraTematica {
    public BotezFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.BOTEZ, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String string = "E momentul să împărtășim o veste minunată cu tine! Te invităm cu drag la un eveniment special - Botezul micuțului nostru îngeraș.\n\n"
                + "Detalii eveniment:\n" + "- Dată: " + this.getDataEveniment() + "\n" + "- Locație: "
                + this.getNumeLocatie() + "\n\n"
                + "Această zi specială va fi marcată de emoții și bucurii, iar prezența ta ne va onora în mod deosebit.\n\n"
                + "Te rugăm să confirmi prezența până la data de " + this.getDataLimitaConfirmare() + ".\n\n"
                + "Suntem nerăbdători să împărtășim această zi cu tine și să creăm amintiri frumoase împreună!\n\n"
                + "Cu drag,\n" + this.getNumeClient();
        System.out.println(string);
    }
}
