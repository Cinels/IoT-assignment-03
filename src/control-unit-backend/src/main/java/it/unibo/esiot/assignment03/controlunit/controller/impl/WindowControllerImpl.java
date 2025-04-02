package it.unibo.esiot.assignment03.controlunit.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.WindowController;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Implementation of {@link WindowController}.
 */
public final class WindowControllerImpl implements WindowController {

    private final Kernel kernel;

    /**
     * Creates a window communication object.
     * @param kernel the kernel of the Control Unit.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the window controller"
            + "need to store data and send stored data."
    )
    public WindowControllerImpl(final Kernel kernel) {
        this.kernel = kernel;
    }

    @Override
    public void modeSwitched() {
        this.kernel.modeSwitched();
    }

    @Override
    public boolean isModeToSwitch() {
        return this.kernel.isModeToSwitch();
    }

    @Override
    public String getCurrentTemperature() {
        return Float.toString(this.kernel.getCurrentTemperature());
    }

    @Override
    public String getNextOpening() {
        return Integer.toString(this.kernel.getNextOpening());
    }

    @Override
    public void setCurrentWindowOpening(final int opening) {
        this.kernel.setCurrentWindowOpening(opening);
    }

    @Override
    public void setWindowMode(final WindowMode mode) {
        this.kernel.setWindowMode(mode);
    }

}
