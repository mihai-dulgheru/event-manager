package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ExpozitieFaraTematica extends EvenimentFaraTematica {
    public ExpozitieFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.EXPOZITIE, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Ne dorim să fiți alături de noi și să ne onorați cu prezența la expoziția pe care o organizăm, ce va avea loc în data de "
                + this.getDataEveniment() + ", la frumoasa " + this.getNumeLocatie() + ".\n\n"
                + "Expoziția va oferi o incursiune fascinantă în lumea artei, "
                + "punând în evidență creativitatea și diversitatea exprimării artistice.\n\n"
                + "Programul evenimentului include:\n"
                + "- Deschiderea porților și primirea invitaților;\n"
                + "- Tur ghidat al expoziției;\n"
                + "- Recepție și discuții libere cu artiștii.\n\n"
                + "Vă rugăm să confirmați participarea până la data limită de "
                + this.getDataLimitaConfirmare() + ".\n\n"
                + "Contăm pe prezența dumneavoastră pentru a face din această expoziție "
                + "un eveniment memorabil.\n\nCu respect,\n" + this.getNumeClient() + ".";
        System.out.println(s);
    }
}
