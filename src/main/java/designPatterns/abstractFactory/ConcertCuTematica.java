package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ConcertCuTematica extends EvenimentCuTematica {
    public ConcertCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.CONCERT, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru concert cu tematica");
    }
}
