package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ExpozitieCuTematica extends EvenimentCuTematica {
    public ExpozitieCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.EXPOZITIE, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru expozitie cu tematica");
    }
}
