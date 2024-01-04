package designPatterns.abstractFactory;

import enums.TipEveniment;
import mvc.model.Eveniment;

import java.util.UUID;

public abstract class EvenimentCuTematica extends Eveniment {
    public EvenimentCuTematica(UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment,
                               Integer nrParticipanti) {
        super(idContract, idLocatie, tipEveniment, dataEveniment, nrParticipanti);
    }

    // TODO: implement this method in the concrete classes
    public abstract void tiparesteInvitatie();
}
