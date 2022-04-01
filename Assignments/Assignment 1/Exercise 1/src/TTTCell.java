import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Bean appearing as a panel composed by two buttons. It represents a cell of
 * the tic-tac-toe grid. A cell is identified by the column and row where it is
 * located and it has to be able to update its appearance when a player makes a 
 * move, when a victory occurs or when a reset is invoked.
 * 
 * @author Leonardo Vona 545042
 */
public class TTTCell extends JPanel implements WinListener, ResetListener {
    /**
     * Identifies the player that moved on this cell. Can be X, O or Initial
     * (if no player made a move on the cell)
     */
    private String state = "Initial";   
    private int xPosition;  // Row where the cell is
    private int yPosition;  // Column where the cell is
    /**
     * When the state of the cell changes, the listeners registered on the event
     * are awakened
     */
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    /**
     * When the state of the cell has to be changed, it has to be approved by 
     * the registered listeners on the event
     */
    private final VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    /**
     * Creates new bean TTTCell
     */
    public TTTCell(){
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonX = new javax.swing.JButton();
        jButtonO = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(100, 100));

        jButtonX.setText("X");
        jButtonX.setPreferredSize(new java.awt.Dimension(100, 45));
        jButtonX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXActionPerformed(evt);
            }
        });
        add(jButtonX);

        jButtonO.setText("O");
        jButtonO.setToolTipText("");
        jButtonO.setName(""); // NOI18N
        jButtonO.setPreferredSize(new java.awt.Dimension(100, 45));
        jButtonO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOActionPerformed(evt);
            }
        });
        add(jButtonO);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * If the button O is pressed, an attempt to change the state of the cell 
     * to O is made.
     */
    private void jButtonOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOActionPerformed
        this.setState("O");
    }//GEN-LAST:event_jButtonOActionPerformed
    
    /**
     * If the button X is pressed, an attempt to change the state of the cell 
     * to X is made.
     */
    private void jButtonXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXActionPerformed
        this.setState("X");
    }//GEN-LAST:event_jButtonXActionPerformed
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (changes != null) {
            changes.addPropertyChangeListener(l);
        }
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
    @Override
    public void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    @Override
    public void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }
    
    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    
    public String getState() {
        return this.state;
    }

    /**
     * Tries to update the value of the state property. Fires a vetoable change
     * and if no listener throws a PropertyVetoException it updates state 
     * property to the value newState and fires a property change to 
     * registered listeners.
     * 
     * @param newState New value of the state property.
     */
    public void setState(String newState) {
        // The value of newState is not an admitted one
        if (!this.state.equals("Initial")
                || (!newState.equals("X")
                && !newState.equals("O"))) {    
            return;
        }
        String oldState = this.state;
        try {
            // Fires a vetoable change
            vetos.fireVetoableChange("state", oldState, newState);
            this.state = newState;
            // Updates the appearance accordingly to the newState value
            if (newState.equals("X")) {
                jButtonX.setBackground(Color.yellow);
                jButtonO.setText("");
                jButtonO.setEnabled(false);
            } else {
                jButtonX.setText("");
                jButtonX.setEnabled(false);
                jButtonO.setBackground(Color.cyan);
            }
            // Fires a property change
            changes.firePropertyChange("state", oldState, newState);
        } catch (PropertyVetoException e) {
            /**
             * The change has been vetoed by a listener, a dialog displaying the 
             * veto reason is shown
             */
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Updates the appearance when a victory happens. Called only if belongs to
     * the triple of cells that caused the victory.
     */
    public void won() {
        if (jButtonX.isEnabled()) {
            jButtonX.setBackground(Color.green);
        } else {
            jButtonO.setBackground(Color.green);
        }
    }
    
    /**
     * Disables the cell if it does not belong to the triple of cells that
     * caused the victory.
     */
    public void disable_cell() {
        jButtonX.setEnabled(false);
        jButtonO.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonO;
    private javax.swing.JButton jButtonX;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Implements {@link WinListener#winOccurred(java.lang.String, int, int,
     * int)} method. Given the parameters, checks if the cell belongs to the
     * triple that caused the victory and calls {@link #won()} or 
     * {@link #disable} accordingly.
     */
    @Override
    public void winOccurred(String winner, int type, int xPosition, int yPosition) {
        // The value of winner parameter is not an admitted one
        if (!"X".equals(winner) && !"O".equals(winner)) {
            return;
        }
        boolean isWinner;
        // The cell has not been pressed by the players
        if (this.state.equals("Initial")) {
            isWinner = false;
        } else if (!this.state.equals(winner)) {    // The cell was pressed by the loser
            isWinner = false;
        } else {    //The cell was marked by the winner
            switch (type) {
                case 1: // Row
                    isWinner = (xPosition == this.xPosition);
                    break;
                case 2: // Column
                    isWinner = (yPosition == this.yPosition);
                    break;
                case 3: // Diagonal
                    isWinner = (this.xPosition == this.yPosition); 
                    break;
                case 4: // Antidiagonal
                    isWinner = (this.xPosition + this.yPosition == 2); 
                    break;
                default:    // Tie or unvalid value
                    return;
            }
        }
        if(isWinner){
            won();
        }else{
            disable_cell();
        }
    }
    /**
     * Implements {@link ResetListener#resetOccurred()} method. Restores the 
     * cell to its initial state.
     */
    @Override
    public void resetOccurred() {
        jButtonX.setBackground(null);
        jButtonX.setEnabled(true);
        jButtonX.setText("X");
        jButtonO.setBackground(null);
        jButtonO.setEnabled(true);
        jButtonO.setText("O");
        this.state = "Initial";
    }

}
