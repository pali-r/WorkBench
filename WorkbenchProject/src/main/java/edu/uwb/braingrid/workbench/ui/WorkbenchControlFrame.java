package edu.uwb.braingrid.workbench.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import edu.uwb.braingrid.workbench.WorkbenchManager;

/**
 * The workbench control frame is responsible for specifying BrainGrid project files. It is the
 * center of all input specification and provenance recording.
 *
 * @author Del Davis, Edited and Updated by Joseph Conquest
 * @version 0.1
 */
public class WorkbenchControlFrame extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Auto-Generated">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    @SuppressWarnings({"deprecation" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(414, 378));

        transferProgressBar = new javax.swing.JProgressBar();
        transferProgressBar.setAlignmentX(-0.5F);
        transferProgressBar.setFocusable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the frame. All child frames are also closed.
     *
     * @param evt - The event that triggered this action
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void viewProvenanceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProvenanceMenuItemActionPerformed
        pack();
        workbenchMgr.viewProvenance();
    }//GEN-LAST:event_viewProvenanceMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar transferProgressBar;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Custom Members">
    private static final long SERIAL_VERSION_UID = 1L;
    private WorkbenchManager workbenchMgr;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Construction">
    /**
     * Responsible for allocating this frame and initializing auto-generated, as well as custom,
     * members.
     */
    public WorkbenchControlFrame() {
        initComponents();
        initCustomMembers();
        center();
    }

    private void initCustomMembers() {
        workbenchMgr = WorkbenchManager.getInstance();

        transferProgressBar.setVisible(false);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="UI Manipulation">
    /**
     * Centers the frame in the operating system's GUI window.
     */
    private void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Fits the window to the maximum width and height of all the contained components. The minimum
     * size is reset to the current size after the pack to make sure that the window can not get any
     * smaller. As in the parent component's implementation of pack, the window will always be
     * within the bounds of it's maximum size.
     */
    @Override
    public void pack() {
        super.pack();
        setMinimumSize(getSize());
    }
    // </editor-fold>
}
