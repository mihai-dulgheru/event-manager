package designPatterns.abstractFactory;

import java.util.UUID;

public class ExpozitieFactory implements EvenimentFactory {
    @Override
    public EvenimentCuTematica createEvenimentCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new ExpozitieCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }

    @Override
    public EvenimentFaraTematica createEvenimentFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new ExpozitieFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }
}
