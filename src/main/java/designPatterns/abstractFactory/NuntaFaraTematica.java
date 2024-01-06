package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class NuntaFaraTematica extends EvenimentFaraTematica {
    public NuntaFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.NUNTA, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Cu inima plină de iubire și fericire, vă invităm să fiți martori la momentul "
                + "magic al uniunii noastre. Nunta noastră va avea loc "
                + "în data de " + this.getDataEveniment() + ", în decorul de poveste al " + this.getNumeLocatie() + ".\n\n"
                + "Vă așteptăm la ceremonia religioasă ce va începe la ora 16:00, urmată de o recepție "
                + "plină de bucurie și dansuri începând cu ora 18:00.\n\n"
                + "Vă rugăm să ne confirmați prezența până cel târziu la data de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Suntem recunoscători să avem oameni minunați ca voi în viața noastră și "
                + "nu vedem ora să împărtășim această zi specială cu voi.\n\n"
                + "Cu iubire și recunoștință,\n" + this.getNumeClient() + " și partenerul său de viață.";
        System.out.println(s);
    }
}
