package interfaces;

import mvc.model.Pachet;
import mvc.model.Serviciu;

public interface Builder {
    Pachet.PachetBuilder addServiciu(Serviciu serviciu);

    Pachet build();
}
