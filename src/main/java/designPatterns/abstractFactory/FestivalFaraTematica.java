package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class FestivalFaraTematica extends EvenimentFaraTematica {
    public FestivalFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.FESTIVAL, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Ne-ar face o deosebită plăcere și bucurie dacă ați participa alături de noi la acest minunat Festival, care se va desfășura în "
                + "zilele de " + this.getDataEveniment() + ", într-o locație atent aleasă, la "
                + this.getNumeLocatie() + ".\n\n"
                + "Festivalul va oferi o experiență autentică, aducând împreună culori, sunete, "
                + "și energie pozitivă.\n\n"
                + "Programul evenimentului:\n"
                + "- Spectacole muzicale și expoziții de artă;\n"
                + "- Ateliere creative și activități în aer liber;\n"
                + "- Standuri cu mâncare și băuturi locale, târg de arte și meșteșuguri.\n\n"
                + "Vă rugăm să confirmați participarea până la data limită de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Contăm pe entuziasmul dumneavoastră pentru a contribui la atmosfera vibrantă a "
                + "acestui festival unic.\n\nCu respect,\n" + this.getNumeClient() + ".";

        System.out.println(s);
    }
}
