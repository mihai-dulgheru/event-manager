package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class NuntaFaraTematica extends EvenimentFaraTematica {
    public NuntaFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.NUNTA, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru nunta fara tematica");
    }
}
