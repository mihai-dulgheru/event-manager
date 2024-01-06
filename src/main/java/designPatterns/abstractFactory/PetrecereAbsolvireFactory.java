package designPatterns.abstractFactory;

import java.util.UUID;

public class PetrecereAbsolvireFactory implements EvenimentFactory {
    @Override
    public EvenimentCuTematica createEvenimentCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        return new PetrecereAbsolvireCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public EvenimentFaraTematica createEvenimentFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new PetrecereAbsolvireFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }
}
