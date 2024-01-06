package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ConcertCuTematica extends EvenimentCuTematica {
    public ConcertCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.CONCERT, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        String s = "Cu bucurie vă invităm la un eveniment muzical de excepție, "
                + "concertul cu tematica \"" + this.getTematica() + "\", care va avea loc "
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
