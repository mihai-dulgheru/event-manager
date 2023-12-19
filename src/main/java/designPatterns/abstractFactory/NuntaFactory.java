package designPatterns.abstractFactory;

import java.util.UUID;

public class NuntaFactory implements EvenimentFactory {
    @Override
    public EvenimentCuTematica createEvenimentCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new NuntaCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }

    @Override
    public EvenimentFaraTematica createEvenimentFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new NuntaFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }
}
