/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JOptionPane;

/**
 *
 * @author matthew
 */
public class ErrorPrompt {

    public static void prompt() {
        JOptionPane.showMessageDialog(null, "Please configure setting in the menu.", "Configure Bloodhound", JOptionPane.ERROR_MESSAGE);

    }
}
