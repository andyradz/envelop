/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.explorer.domain.form;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author andrzej.radziszewski
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    public frmMain() {
		setMinimumSize(new Dimension(800, 600));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAlwaysOnTop(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 784, 0 };
		gridBagLayout.rowHeights = new int[] { 93, 93, 93, 93, 60, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		getContentPane().add(panel_1, gbc_panel_1);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panel_2, gbc_panel_2);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		getContentPane().add(panel_3, gbc_panel_3);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 3;
		getContentPane().add(panel_4, gbc_panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setFocusable(false);
		panel_5.setFocusTraversalKeysEnabled(false);
		panel_5.setBackground(new Color(233, 150, 122));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 0, 0);
		gbc_panel_5.weightx = 0;
		gbc_panel_5.weighty = 0;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 4;
		getContentPane().add(panel_5, gbc_panel_5);
		panel_5.setLayout(new GridLayout(1, 7, 0, 0));

		JButton btnNewButton_5 = new JButton("F3 Podgląd");
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_5.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setDoubleBuffered(true);
		btnNewButton_5.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_5.setIcon(
				new ImageIcon(
						frmMain.class.getResource(
								"/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Indent@2x-rtl.png")));
		btnNewButton_5.setVerifyInputWhenFocusTarget(false);
		btnNewButton_5.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("F4 Edycja");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_6.setMnemonic(KeyEvent.VK_PROPS);
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setRolloverEnabled(false);
		btnNewButton_6.setRequestFocusEnabled(false);
		btnNewButton_6.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_6);

		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAlignmentY(0.0f);
		panel_5.add(btnNewButton_1);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAlignmentY(0.0f);
		panel_5.add(btnNewButton);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                new frmMain().setVisible(true);
            }

        });
    }
    // End of variables declaration//GEN-END:variables
}
