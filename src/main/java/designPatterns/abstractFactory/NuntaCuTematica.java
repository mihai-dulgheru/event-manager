package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class NuntaCuTematica extends EvenimentCuTematica {
    public NuntaCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.NUNTA, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
       String s = "Cu bucurie și emoție vă invităm la celebrarea dragostei, "
                + "cu ocazia nunții noastre cu tematica \"" + this.getTematica() + "\", care va avea loc "
                + "în data de, " + this.getDataEveniment() + ", la frumoasa " + this.getNumeLocatie() + ".\n\n"
                + "Ceremonia religioasă va avea loc la ora 16:00, iar petrecerea se va desfășura "
                + "începând cu ora 18:00.\n\n"
                + "Vă rugăm să ne confirmați prezența până la data limită de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Suntem nerăbdători să împărtășim acest moment special cu voi și să creăm amintiri de neuitat.\n\n"
                + "Cu dragoste,\n" + this.getNumeClient() + " și partenerul său de viață.";
        System.out.println(s);
    }
}
