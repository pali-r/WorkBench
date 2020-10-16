package edu.uwb.braingrid.workbenchdashboard.simstarter;

import java.util.logging.Logger;
import javax.swing.JFileChooser;
import edu.uwb.braingrid.workbench.WorkbenchManager;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimManager {
	private static final Logger LOG = Logger.getLogger(SimManager.class.getName());
	public SimManager() { //Tab tab    was param
		//super(tab);
		LOG.info("new " + getClass().getName());
//		ssas_ = new SimStarterAttributesSelector(this, workbenchManager);
//		disableProjectAttributeRelatedButtons();
		//initCenter();
		//super.setTitle("SimManager");
	}

	public void openProject() {
		int code = workbenchManager.openProject();
		switch (code) {
		case JFileChooser.APPROVE_OPTION:
			updateProjectOverview();
			break;
		case JFileChooser.CANCEL_OPTION:
			break;
		case JFileChooser.ERROR_OPTION:
			break;
		case WorkbenchManager.EXCEPTION_OPTION:
		default:
			ssas_.resetUILabelText();
			break;
		}
	}

	public void saveProject() {
		workbenchManager.saveProject();
		setMsg();
	}

	public void viewProvenance() {
		setMsg();
		workbenchManager.viewProvenance();
	}

	public void manageParamsClasses() {
		if (workbenchManager.configureParamsClasses()) {
			updateProjectOverview();
		}
		setMsg();
	}

	private void initCenter() {
		project_title_label_.setText("Current Project: ");
		current_proj_lbl_.setText("None");
		HBox proj = new HBox(project_title_label_, current_proj_lbl_);
		// VBox center = new VBox(proj, initAttributeDisplay(), msgText);
		VBox center = new VBox(proj, ssas_, msgText);
		bp_.setCenter(center);
	}

	public void updateProjectOverview() {
		current_proj_lbl_.setText(workbenchManager.getProjectName());
		// transferProgressBar.setVisible(workbenchManager.isSimExecutionRemote());
		ssas_.updateProject();
		// enableInitialButtons();
	}

	public void disableProjectAttributeRelatedButtons() {
	//	ssas_.disableButtons();
	}

	/**
	 * Sets the workbench message content. The content of this message is based on
	 * the accumulated messages produced by the functions of the workbench manager.
	 *
	 */
	public void setMsg() {
		msgText.setText(workbenchManager.getMessages());
	}

	
	private BorderPane bp_ = new BorderPane();

	private Label project_title_label_ = new Label();
	private Label current_proj_lbl_ = new Label();
	private ProgressBar progress_bar_ = new ProgressBar(0);
	private TextArea msgText = new TextArea("");

	private WorkbenchManager workbenchManager = new WorkbenchManager();
	private SimStarterAttributesSelector ssas_;
}
