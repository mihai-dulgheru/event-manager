package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class PetrecereAbsolvireFaraTematica extends EvenimentFaraTematica {
    public PetrecereAbsolvireFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, TipEveniment.PETRECERE_ABSOLVIRE, dataEveniment, nrParticipanti);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru petrecere absolvire fara tematica");
    }
}
