
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/BeanForm.java to edit this template
 */

/**
 *
 * @author leonardo
 */
public class TempLabel extends JLabel {

    /**
     * Creates new form TempLabel
     */
    public TempLabel() {
        initComponents();
    }

    @Override
    public void setText(String text) {
        float temperature = 0;
        try{
            temperature = Float.parseFloat(text);
        }catch(NumberFormatException e){
            super.setText("NaN");
        } 
        temperature = (temperature * (float)9/5) + 32;
        super.setText(Float.toString(temperature)); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
