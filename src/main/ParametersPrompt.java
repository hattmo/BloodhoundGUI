/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author matthew
 */
public class ParametersPrompt extends javax.swing.JPanel {

    /**
     * Creates new form ParametersPrompt
     */
    private ParametersPrompt() {
        leftListModel = new MyListModel();
        rightListModel = new MyListModel();

        initComponents();
    }

    public static void prompt() {
        ParametersPrompt p = new ParametersPrompt();

        File confFile = new File("bloodhound/bhparam.conf");

        if (confFile.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(confFile))) {
                Scanner s = new Scanner(reader);
                while (s.hasNext()) {
                    switch (s.next()) {
                        case "-CollectionMethod":
                            String collect = s.next();
                            if (collect.contains("Group")) {
                                p.rightListModel.addItem("Group");
                            } else {
                                p.leftListModel.addItem("Group");
                            }
                            if (collect.contains("LocalGroup")) {
                                p.rightListModel.addItem("LocalGroup");
                            } else {
                                p.leftListModel.addItem("LocalGroup");
                            }
                            if (collect.contains("Session")) {
                                p.rightListModel.addItem("Session");
                            } else {
                                p.leftListModel.addItem("Session");
                            }
                            if (collect.contains("SessionLoop")) {
                                p.rightListModel.addItem("SessionLoop");
                            } else {
                                p.leftListModel.addItem("SessionLoop");
                            }
                            if (collect.contains("Trusts")) {
                                p.rightListModel.addItem("Trusts");
                            } else {
                                p.leftListModel.addItem("Trusts");
                            }
                            if (collect.contains("ACL")) {
                                p.rightListModel.addItem("ACL");
                            } else {
                                p.leftListModel.addItem("ACL");
                            }
                            if (collect.contains("ComputerOnly")) {
                                p.rightListModel.addItem("ComputerOnly");
                            } else {
                                p.leftListModel.addItem("ComputerOnly");
                            }
                            if (collect.contains("GPOLocalGroup")) {
                                p.rightListModel.addItem("GPOLocalGroup");
                            } else {
                                p.leftListModel.addItem("GPOLocalGroup");
                            }
                            if (collect.contains("LoggedOn")) {
                                p.rightListModel.addItem("LoggedOn");
                            } else {
                                p.leftListModel.addItem("LoggedOn");
                            }
                            if (collect.contains("ObjectProps")) {
                                p.rightListModel.addItem("ObjectProps");
                            } else {
                                p.leftListModel.addItem("ObjectProps");
                            }
                            break;
                        case "-Stealth":
                            p.stealthChk.setSelected(true);
                            break;
                        case "-ExcludeDC":
                            p.excludedcChk.setSelected(true);
                            break;
                        case "-LoopTime":
                            p.loopintSpn.setValue(Integer.parseInt(s.next()));
                            break;
                        case "-Throttle":
                            p.throttleSpn.setValue(Integer.parseInt(s.next()));
                            break;
                        case "-Jitter":
                            p.jitterSpn.setValue(Integer.parseInt(s.next()));
                            break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ParametersPrompt.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            p.rightListModel.addItem("Group");
            p.rightListModel.addItem("LocalGroup");
            p.rightListModel.addItem("Session");
            p.leftListModel.addItem("SessionLoop");
            p.rightListModel.addItem("Trusts");
            p.leftListModel.addItem("ACL");
            p.leftListModel.addItem("ComputerOnly");
            p.leftListModel.addItem("GPOLocalGroup");
            p.leftListModel.addItem("LoggedOn");
            p.leftListModel.addItem("ObjectProps");
        }

        int showConfirmDialog = JOptionPane.showConfirmDialog(null, p, "Configure Bloodhound", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (showConfirmDialog == JOptionPane.OK_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(confFile))) {

                if (p.rightListModel.data.size() > 0) {
                    writer.write("-CollectionMethod ");
                    boolean first = true;
                    for (String method : p.rightListModel.data) {
                        if (!first) {
                            writer.write(",");
                        } else {
                            first = false;
                        }
                        writer.write(method);
                    }
                }
                if (p.stealthChk.isSelected()) {
                    writer.write(" -Stealth");
                }
                if (p.excludedcChk.isSelected()) {
                    writer.write(" -ExcludeDC");
                }
                if ((Integer) p.loopintSpn.getValue() != 5 && p.rightListModel.data.contains("SessionLoop")) {
                    writer.write(" -LoopTime " + p.loopintSpn.getValue());
                }
                if ((Integer) p.throttleSpn.getValue() != 0) {
                    writer.write(" -Throttle " + p.throttleSpn.getValue());
                }
                if ((Integer) p.jitterSpn.getValue() != 0) {
                    writer.write(" -Jitter " + p.jitterSpn.getValue());
                }

                writer.flush();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to save settings.", "Configure Bloodhound", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Settings not saved.", "Configure Bloodhound", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        leftLst = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        rightLst = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        moveLeftBtn = new javax.swing.JButton();
        moveRightBtn = new javax.swing.JButton();
        stealthChk = new javax.swing.JCheckBox();
        collectionLbl = new javax.swing.JLabel();
        activeLbl = new javax.swing.JLabel();
        availableLbl = new javax.swing.JLabel();
        excludedcChk = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        loopintSpn = new javax.swing.JSpinner();
        loopintLbl = new javax.swing.JLabel();
        loopminLbl = new javax.swing.JLabel();
        trottleLbl = new javax.swing.JLabel();
        jitterLbl = new javax.swing.JLabel();
        throttleSpn = new javax.swing.JSpinner();
        jitterSpn = new javax.swing.JSpinner();
        throttlemsLbl = new javax.swing.JLabel();
        percentLbl = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 300));

        leftLst.setModel(leftListModel);
        leftLst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(leftLst);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(150, 300));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 300));

        rightLst.setModel(rightListModel);
        rightLst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(rightLst);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        moveLeftBtn.setText("<");
        moveLeftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveLeftBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(moveLeftBtn, gridBagConstraints);

        moveRightBtn.setText(">");
        moveRightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveRightBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(moveRightBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(jPanel1, gridBagConstraints);

        stealthChk.setText("Stealth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(stealthChk, gridBagConstraints);

        collectionLbl.setText("Collection Methods");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(collectionLbl, gridBagConstraints);

        activeLbl.setText("Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(activeLbl, gridBagConstraints);

        availableLbl.setText("Available");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(availableLbl, gridBagConstraints);

        excludedcChk.setText("Exclude DC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(excludedcChk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jSeparator1, gridBagConstraints);

        loopintSpn.setModel(new javax.swing.SpinnerNumberModel(5, 1, null, 1));
        loopintSpn.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(loopintSpn, gridBagConstraints);

        loopintLbl.setText("Loop Interval");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(loopintLbl, gridBagConstraints);

        loopminLbl.setText("min");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(loopminLbl, gridBagConstraints);

        trottleLbl.setText("Request Throttle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(trottleLbl, gridBagConstraints);

        jitterLbl.setText("Jitter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jitterLbl, gridBagConstraints);

        throttleSpn.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        throttleSpn.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(throttleSpn, gridBagConstraints);

        jitterSpn.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 5));
        jitterSpn.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jitterSpn, gridBagConstraints);

        throttlemsLbl.setText("ms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(throttlemsLbl, gridBagConstraints);

        percentLbl.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(percentLbl, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void moveRightBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveRightBtnActionPerformed
        int leftIndex = leftLst.getSelectedIndex();
        if (leftIndex != -1) {
            rightListModel.addItem(leftListModel.getElementAt(leftIndex));
            leftListModel.removeItem(leftIndex);
        }

    }//GEN-LAST:event_moveRightBtnActionPerformed

    private void moveLeftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveLeftBtnActionPerformed
        int rightIndex = rightLst.getSelectedIndex();
        if (rightIndex != -1) {
            leftListModel.addItem(rightListModel.getElementAt(rightIndex));
            rightListModel.removeItem(rightIndex);
        }
    }//GEN-LAST:event_moveLeftBtnActionPerformed

    private MyListModel leftListModel;
    private MyListModel rightListModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeLbl;
    private javax.swing.JLabel availableLbl;
    private javax.swing.JLabel collectionLbl;
    private javax.swing.JCheckBox excludedcChk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jitterLbl;
    private javax.swing.JSpinner jitterSpn;
    private javax.swing.JList<String> leftLst;
    private javax.swing.JLabel loopintLbl;
    private javax.swing.JSpinner loopintSpn;
    private javax.swing.JLabel loopminLbl;
    private javax.swing.JButton moveLeftBtn;
    private javax.swing.JButton moveRightBtn;
    private javax.swing.JLabel percentLbl;
    private javax.swing.JList<String> rightLst;
    private javax.swing.JCheckBox stealthChk;
    private javax.swing.JSpinner throttleSpn;
    private javax.swing.JLabel throttlemsLbl;
    private javax.swing.JLabel trottleLbl;
    // End of variables declaration//GEN-END:variables

}
