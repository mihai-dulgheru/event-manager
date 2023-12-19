package designPatterns.abstractFactory;

import java.util.UUID;

public class ConcertFactory implements EvenimentFactory {
    @Override
    public EvenimentCuTematica createEvenimentCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new ConcertCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }

    @Override
    public EvenimentFaraTematica createEvenimentFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new ConcertFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }
}
