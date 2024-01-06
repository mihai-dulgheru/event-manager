package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ConcertFaraTematica extends EvenimentFaraTematica {
    public ConcertFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.CONCERT, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Vă așteptăm cu bucurie să participați la acest eveniment muzical deosebit, un concert magic" + "\" care va avea loc "
                + this.getDataEveniment() + " la prestigioasa " + this.getNumeLocatie() + ".\n\n"
                + "După finalul concertului, vă invităm la o recepție exclusivă unde veți avea "
                + "ocazia să vă întâlniți și să discutați cu artiștii.\n\n"
                + "Pentru a vă rezerva locul la acest spectacol unic, vă rugăm să confirmați prezența "
                + "până la data limită de " + this.getDataLimitaConfirmare() + " prin e-mail la "
                + "confirmare@armoniamuzicala.com.\n\nDress code: Casual elegant.\n\n"
                + "Cu respect,\n" + this.getNumeClient() + ".";
        System.out.println(s);
    }
}
