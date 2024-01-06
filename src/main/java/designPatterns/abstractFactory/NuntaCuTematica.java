package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class NuntaCuTematica extends EvenimentCuTematica {
    public NuntaCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.NUNTA, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru nunta cu tematica");
    }
}
