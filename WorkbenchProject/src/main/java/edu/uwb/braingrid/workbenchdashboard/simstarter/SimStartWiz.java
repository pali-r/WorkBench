package edu.uwb.braingrid.workbenchdashboard.simstarter;

import java.util.logging.Logger;
import java.util.HashMap;
import javafx.scene.control.TextArea;

import edu.uwb.braingrid.workbench.WorkbenchManager;
import edu.uwb.braingrid.workbench.utils.DateTime;
import edu.uwb.braingrid.workbench.ui.SimulationRuntimeDialog;

/**
 * The SimStartWiz is responsible for handling all back-end events associated with running the
 * simulation start wizard.
 *
 * @author Joseph Conquest
 * @version 1.3
 */
public class SimStartWiz {

    private static final Logger LOG = Logger.getLogger(SimStartWiz.class.getName());

    private TextArea msgText = new TextArea("");
    private WorkbenchManager workbenchManager = WorkbenchManager.getInstance();
    private SimulationRuntimeDialog srd;
    private String commitVersionSelected = null;

    /**
     * Responsible for allocating this frame and initializing auto-generated, as well as custom,
     * members.
     */
    public SimStartWiz() {
        LOG.info("new " + getClass().getName());
        if (workbenchManager.newSimulation()) {
            if (configureSimulation()) {
                if (specifySimulation()) {
                    if (generateScript()) {
                        if (runScript()) {
                            srd = new SimulationRuntimeDialog(msgText);
                        }
                    }
                }
            }
        }
    }

    /**
     * Responsible for allocating this frame and initializing auto-generated, as well as custom,
     * members.
     *
     * @param simSpecifications  Inputs for configuring Sim
     * @param runtimeSpecifications  Git commit version to be pulled
     * @param nListPresets  Neuron list inputs
     */
    public SimStartWiz(String simSpecifications, String runtimeSpecifications,
            HashMap<Character, String> nListPresets) {
        LOG.info("new " + getClass().getName());
        if (workbenchManager.newSimulation()) {
            if (configureSimulation(simSpecifications, nListPresets)) {
                if (specifySimulation(runtimeSpecifications)) {
                    if (generateScript()) {
                        if (runScript()) {
                            srd = new SimulationRuntimeDialog(msgText);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the workbench message content. The content of this message is based on the accumulated
     * messages of produced by the functions of the workbench manager.
     */
    public void setMsg() {
        msgText.setText(workbenchManager.getMessages());
    }

    /**
     * Prompts the user to select files for the simulation input. InputAnalyzer files are created
     * with NLEdit or by hand in XML. InputAnalyzer files represent lists of neurons with regard to
     * their position in a neuron array (e.g. position 12 is x: 1, y: 2 on a 10x10 grid)
     *
     * @return True if the simulation was configured successfully, false otherwise
     */
    private boolean configureSimulation() {
        boolean wasSuccessful = false;
        if (workbenchManager.configureSimulation()) {
            workbenchManager.invalidateScriptGenerated();
            wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }

    /**
     * Prompts the user to select files for the simulation input. InputAnalyzer files are created
     * with NLEdit or by hand in XML. InputAnalyzer files represent lists of neurons with regard to
     * their position in a neuron array (e.g. position 12 is x: 1, y: 2 on a 10x10 grid)
     *
     * @param simInputPresets  Inputs for configuring Sim
     * @param nListPresets  Neuron list inputs
     * @return True if the simulation was configured successfully, false otherwise
     */
    private boolean configureSimulation(String simInputPresets,
            HashMap<Character, String> nListPresets) {
        if (simInputPresets == null && nListPresets == null) {
            return configureSimulation();
        }
        boolean wasSuccessful = false;
        if (workbenchManager.configureSimulation(simInputPresets, nListPresets)) {
            workbenchManager.invalidateScriptGenerated();
            wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }

    /**
     * Prompts the user to specify the simulation context. This should be the file that was invoked,
     * which used the input files specified, in order to write the output file that was specified.
     *
     * @return True if the simulation specification was successful, false otherwise
     */
    private boolean specifySimulation() {
        boolean wasSuccessful = false;
        if (workbenchManager.specifySimulation()) {
            workbenchManager.invalidateScriptGenerated();
            wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }

    /**
     * Prompts the user to specify the simulation context. This should be the file that was invoked,
     * which used the input files specified, in order to write the output file that was specified.
     *
     * @param runtimeSpecifications  Git commit version to be pulled
     * @return True if the simulation specification was successful, false otherwise
     */
    private boolean specifySimulation(String runtimeSpecifications) {
        if (runtimeSpecifications == null) {
            return specifySimulation();
        }
        boolean wasSuccessful = false;
        if (workbenchManager.specifySimulation(runtimeSpecifications)) {
        workbenchManager.invalidateScriptGenerated();
        wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }

    private boolean generateScript() {
        boolean wasSuccessful = false;
        if (workbenchManager.generateScript()) {
            wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }

    /**
     * Runs the script on the remote host. Connection information is entered in a
     * SSHConnectionDialog.
     *
     * @return True if the script ran successfully, false otherwise
     */
    private boolean runScript() {
        boolean wasSuccessful = false;
        if (workbenchManager.runScript()) {
            String time = DateTime.getTime(DateTime.now());
            String msg = "Script execution started at: " + time;
            wasSuccessful = true;
        }
        setMsg();
        return wasSuccessful;
    }
}
