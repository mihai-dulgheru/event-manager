package designPatterns.abstractFactory;

import enums.TipEveniment;

import java.util.UUID;

public class PetrecereAbsolvireCuTematica extends EvenimentCuTematica {
    public PetrecereAbsolvireCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        super(idContract, idLocatie, TipEveniment.PETRECERE_ABSOLVIRE, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public void tiparesteInvitatie() {
        System.out.println("Tiparim invitatie pentru petrecere absolvire cu tematica");
    }
}
