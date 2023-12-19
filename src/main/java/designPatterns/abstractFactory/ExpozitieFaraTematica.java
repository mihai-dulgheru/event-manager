package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class ExpozitieFaraTematica extends EvenimentFaraTematica{
    public ExpozitieFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.EXPOZITIE, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru expozitie fara tematica");
    }
}
