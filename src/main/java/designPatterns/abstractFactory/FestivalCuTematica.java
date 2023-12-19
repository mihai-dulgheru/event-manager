package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class FestivalCuTematica extends EvenimentCuTematica {
    public FestivalCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.FESTIVAL, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru festival cu tematica");
    }
}
