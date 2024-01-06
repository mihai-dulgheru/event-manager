package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class PetrecereAbsolvireCuTematica extends EvenimentCuTematica {
    public PetrecereAbsolvireCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.PETRECERE_ABSOLVIRE, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {

        String s = "Cu mare bucurie vă invităm să sărbătorim împreună momentul important al absolvirii. "
                + "Petrecerea noastră de absolvire, cu tema \"" + this.getTematica() + "\", va avea loc "
                + "în data de " + this.getDataEveniment() + ", la elegantul loc " + this.getNumeLocatie() + ".\n\n"
                + "Programul petrecerii:\n"
                + "- 18:00: Recepție și înregistrare;\n"
                + "- 19:00: Discursuri și momente de reflectare;\n"
                + "- 21:00: Petrecere și dans.\n\n"
                + "Vă rugăm să confirmați participarea până cel târziu la data de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Această petrecere marchează nu doar sfârșitul unei etape, ci și începutul unei noi călătorii. "
                + "Suntem mândri de reușitele voastre și abia așteptăm să celebrăm împreună.\n\n"
                + "Cu căldură,\n" + this.getNumeClient();
        System.out.println(s);
    }
}
