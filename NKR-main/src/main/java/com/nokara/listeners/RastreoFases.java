
package com.nokara.listeners;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

import java.io.Serializable;
import java.util.logging.Logger;

public class RastreoFases implements PhaseListener, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER =
            Logger.getLogger(RastreoFases.class.getName());

    @Override
    public void beforePhase(PhaseEvent event) {
        LOGGER.info("INICIO DE FASE: " + event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LOGGER.info("FIN DE FASE: " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
