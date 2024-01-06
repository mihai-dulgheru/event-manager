package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class PetrecereAbsolvireFaraTematica extends EvenimentFaraTematica {
    public PetrecereAbsolvireFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.PETRECERE_ABSOLVIRE, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Este cu mare bucurie că vă invităm să sărbătorim reușita și realizările noastre la petrecerea "
                + "de absolvire, care va avea loc în data de, "
                + this.getDataEveniment() + ", la prestigioasa locație " + this.getNumeLocatie() + ".\n\n"
                + "Detalii despre eveniment:\n"
                + "- 17:30: Sosire și înregistrare;\n"
                + "- 18:00: Ceremonia de absolvire și discursuri inspiraționale;\n"
                + "- 20:00: Petrecere cu muzică și dansuri.\n\n"
                + "Vă rugăm să confirmați participarea până la data de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Această petrecere marchează nu doar sfârșitul unei capitole importante, ci și deschiderea "
                + "unei noi cărți pline de aventuri și realizări. Sperăm să împărtășim aceste momente de bucurie "
                + "și mândrie alături de voi.\n\n"
                + "Cu admirație și entuziasm,\n" + this.getNumeClient();
        System.out.println(s);
    }
}
