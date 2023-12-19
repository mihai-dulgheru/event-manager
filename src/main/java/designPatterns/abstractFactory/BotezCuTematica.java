package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class BotezCuTematica extends EvenimentCuTematica {
    public BotezCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.BOTEZ, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru botez cu tematica");
    }
}
