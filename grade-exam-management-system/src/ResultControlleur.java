import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.*;
public class ResultControlleur {
	public void getNote(JComboBox mCMB, JComboBox nCMB,DefaultTableModel mdl) {
        Matiere m = (Matiere) mCMB.getSelectedItem();
        Etudiant et = (Etudiant) nCMB.getSelectedItem();
        Correcteur co1 = null, co2 = null;

        try {
        	 ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(m + "_" + "cor 1"));
             ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(m + "_" + "cor 2"));
            co1 = (Correcteur) ois1.readObject();
            co2 = (Correcteur) ois2.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

        Correction cor = new Correction(co1, co2);
        double note = cor.noteFinal();
        mdl.addRow(new Object[]{et, m, note});
	}

}
