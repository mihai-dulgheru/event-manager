package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class BotezFaraTematica extends EvenimentFaraTematica {
    public BotezFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.BOTEZ, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru botez fara tematica");
    }
}
