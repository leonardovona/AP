
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean that represents a tic-tac-toe board. It is formed by nine cell beans, a
 * controller bean and a button. It listens for changes of the state of the
 * cells and checks if the game is over due to a tie or to a victory.
 *
 * @author Leonardo Vona 545042
 */
public class TTTBoard extends javax.swing.JFrame implements PropertyChangeListener {

    private final TTTCell cells[][] = new TTTCell[3][3];
    private int emptyCells = 9; // Used to check for a tie

    // Counters to check for a victory
    private final int rows[] = new int[3];
    private final int cols[] = new int[3];
    private int diag;
    private int antiDiag;

    // Lists of Win and Reset event listeners
    private final List<WinListener> winListeners = new ArrayList<>();
    private final List<ResetListener> resetListeners = new ArrayList<>();

    /**
     * Creates new bean TTTBoard
     */
    public TTTBoard() {
        initComponents();
        /**
         * Rearranges the cells in a more compacted and useful way with respect
         * to NetbeansGUI builder
         */
        cells[0][0] = tTTCell1;
        cells[0][1] = tTTCell2;
        cells[0][2] = tTTCell3;
        cells[1][0] = tTTCell4;
        cells[1][1] = tTTCell5;
        cells[1][2] = tTTCell6;
        cells[2][0] = tTTCell7;
        cells[2][1] = tTTCell8;
        cells[2][2] = tTTCell9;

        // Sets the correct properties of the cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setXPosition(i);
                cells[i][j].setYPosition(j);
                this.winListeners.add(cells[i][j]);
                this.resetListeners.add(cells[i][j]);
                cells[i][j].addPropertyChangeListener(this);
                cells[i][j].addVetoableChangeListener(tTTController1);
            }
        }
        this.winListeners.add(tTTController1);
        this.resetListeners.add(tTTController1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tTTCell1 = new TTTCell();
        tTTCell2 = new TTTCell();
        tTTCell3 = new TTTCell();
        tTTCell4 = new TTTCell();
        tTTCell5 = new TTTCell();
        tTTCell6 = new TTTCell();
        tTTCell7 = new TTTCell();
        tTTCell8 = new TTTCell();
        tTTCell9 = new TTTCell();
        tTTController1 = new TTTController();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setBackground(java.awt.Color.red);
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jButton1.setText("RESTART");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(100, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tTTCell1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tTTCell4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tTTCell7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTTCell9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tTTController1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tTTCell3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tTTCell4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tTTCell7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTCell9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTTController1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Reset button has been pressed, fires reset event to all reset listeners
     * and restores the initial state of the board
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (ResetListener l : resetListeners) {
            l.resetOccurred();
        }
        for (int i = 0; i < 3; i++) {
            rows[i] = 0;
            cols[i] = 0;
        }
        emptyCells = 9;
        diag = 0;
        antiDiag = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTTBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTTBoard().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private TTTCell tTTCell1;
    private TTTCell tTTCell2;
    private TTTCell tTTCell3;
    private TTTCell tTTCell4;
    private TTTCell tTTCell5;
    private TTTCell tTTCell6;
    private TTTCell tTTCell7;
    private TTTCell tTTCell8;
    private TTTCell tTTCell9;
    private TTTController tTTController1;
    // End of variables declaration//GEN-END:variables

    /**
     * Fired by a cell after a player made her / his move. Updates the internal
     * state of the board according to the cells that has been marked and to the
     * player that moved. After the update checks if a victory occurred or the
     * game is over with a tie calling the method checkWin.
     *
     * @param event Includes the source of the event, used to discriminate the
     * cell that fired the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        boolean found = false;
        for (int i = 0; i < 3 && !found; i++) {
            for (int j = 0; j < 3 && !found; j++) {
                if (event.getSource() == cells[i][j]) { // The cell i,j is the source of the event
                    /**
                     * The player X increments by 1 the counters, whereas the
                     * player O increments them by 4. This is needed to check
                     * for a victory efficiently
                     */
                    if (cells[i][j].getState().equals("X")) {   // The player X moved

                        if (i == j) {   // The cell is in the diagonal
                            diag++;
                        }
                        if (i + j == 2) { // The cell is in the antidiagonal
                            antiDiag++;
                        }
                        rows[i]++;
                        cols[j]++;
                        emptyCells--;
                    } else if (cells[i][j].getState().equals("O")) {    // The player O moved
                        if (i == j) {   // The cell is in the diagonal
                            diag += 4;
                        }
                        if (i + j == 2) { // The cell is in the antidiagonal
                            antiDiag += 4;
                        }
                        rows[i] += 4;
                        cols[j] += 4;
                        emptyCells--;
                    }
                    checkWin(i, j);
                    found = true;
                }
            }
        }
    }

    /**
     * Checks if a victory or a tie occurred using the internal state of the
     * board. In case of a win it fires a Win event.
     *
     * @param i Row of the last marked cell. Used to determine the (eventual)
     * winning cells.
     * @param j Column of the last marked cell. Used to determine the (eventual)
     * winning cells.
     */
    public void checkWin(int i, int j) {
        String winner = null;
        int victoryType = 0; // 0 -> tie, 1 -> row, 2 -> column, 3 -> diag, 4 -> antidiagonal

        /**
         * If a counter has value 3, X has won. If a counter has value 12, O has
         * won. Otherwise the game is not over or a tie occurred
         */
        for (int h = 0; h < 3; h++) { // Checks if a victory happened by row or column
            if (rows[h] == 3) {
                victoryType = 1;
                winner = "X";
                break;
            }
            if (rows[h] == 12) {
                victoryType = 1;
                winner = "O";
                break;
            }
            if (cols[h] == 3) {
                victoryType = 2;
                winner = "X";
                break;
            }
            if (cols[h] == 12) {
                victoryType = 2;
                winner = "O";
                break;
            }
        }
        // Checks win by diagonal or antidiagonal
        if (diag == 3) {
            victoryType = 3;
            winner = "X";
        }
        if (diag == 12) {
            victoryType = 3;
            winner = "O";
        }
        if (antiDiag == 3) {
            victoryType = 4;
            winner = "X";
        }
        if (antiDiag == 12) {
            victoryType = 4;
            winner = "O";
        }

        if (victoryType == 0 && emptyCells > 0) {   // No victory and game not over
            return;
        }

        // A victory or a tie occurred. The event Win is fired to all the listeners
        for (WinListener l : winListeners) {
            l.winOccurred(winner, victoryType, i, j);
        }
    }
}
