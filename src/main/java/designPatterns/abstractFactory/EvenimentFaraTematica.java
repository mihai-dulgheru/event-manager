package designPatterns.abstractFactory;

import enums.TipEveniment;
import mvc.model.Eveniment;

import java.util.UUID;

public abstract class EvenimentFaraTematica extends Eveniment {
    public EvenimentFaraTematica(UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment, Integer nrParticipanti) {
        super(idContract, idLocatie, tipEveniment, dataEveniment, nrParticipanti, null);
    }

    public abstract void tiparesteInvitatie();
}
