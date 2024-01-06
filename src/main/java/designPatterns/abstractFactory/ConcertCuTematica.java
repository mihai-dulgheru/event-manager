package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ConcertCuTematica extends EvenimentCuTematica {
    public ConcertCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.CONCERT, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru concert cu tematica");
    }
}
