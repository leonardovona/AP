
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.JLabel;

/**
 * Bean appearing as a label. It has to check the correct execution of the game.
 * Indicates the next player that has to move and, in case of termination, who
 * is the winner, if any.
 *
 * @author Leonardo Vona 545042
 */
public class TTTController extends JLabel implements VetoableChangeListener, WinListener, ResetListener {

    private String nextMove = null; // Next player to move (O or X)

    /**
     * Creates new bean TTTController.
     */
    public TTTController() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setText("START GAME");
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(150, 30));
        setRequestFocusEnabled(false);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Checks if the move attempted by a player is admitted. It verifies that
     * the player that wants to move is the one admitted to.
     *
     * @param event The new value field is the player attempting to move.
     * @throws PropertyVetoException Thrown when the player attempting to move
     * is not the correct one.
     */
    @Override
    public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
        if (nextMove == null
                || "X".equals(nextMove) && "X".equals(event.getNewValue())
                || "O".equals(nextMove) && "O".equals(event.getNewValue())) {
            if (event.getNewValue().equals("X")) {
                nextMove = "O";
                this.setBackground(Color.cyan);
            } else {
                nextMove = "X";
                this.setBackground(Color.yellow);
            }
            this.setText("NEXT MOVE: " + nextMove);
            return;
        }
        throw new PropertyVetoException("The next one to move must be " + nextMove, event);
    }

    /**
     * Implements {@link WinListener#winOccurred(java.lang.String, int, int,
     * int)} method. Checks if the game ended with a tie or a victory, and
     * updates its appearance accordingly.
     */
    @Override
    public void winOccurred(String winner, int type, int xPosition, int yPosition) {
        if (type == 0) { // Tie
            this.setText("TIE");
            this.setBackground(Color.orange);
        } else {
            this.setText("THE WINNER IS: " + winner);
            this.setBackground(Color.green);
        }
    }

    /**
     * Implements {@link ResetListener#resetOccurred()} method. Reset the
     * appearance of the bean and its internal state.
     */
    @Override
    public void resetOccurred() {
        this.setBackground(null);
        this.setText("START GAME");
        this.nextMove = null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
