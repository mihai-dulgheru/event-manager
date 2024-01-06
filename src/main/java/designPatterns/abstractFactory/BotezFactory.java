package designPatterns.abstractFactory;

import java.util.UUID;

public class BotezFactory implements EvenimentFactory {
    @Override
    public EvenimentCuTematica createEvenimentCuTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti, String tematica) {
        return new BotezCuTematica(idContract, idLocatie, dataEveniment, nrParticipanti, tematica);
    }

    @Override
    public EvenimentFaraTematica createEvenimentFaraTematica(UUID idContract, UUID idLocatie, String dataEveniment, Integer nrParticipanti) {
        return new BotezFaraTematica(idContract, idLocatie, dataEveniment, nrParticipanti);
    }
}
