package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class FestivalFaraTematica extends EvenimentFaraTematica {
    public FestivalFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.FESTIVAL, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru festival fara tematica");
    }
}
