import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Home extends JFrame{
	JRadioButton etRDB,ensRDB,emRDB,resRDB;
	JButton etudiantBTN,employeeBTN,enseignantBTN,resultBTN;
	JTabbedPane empJTP,etJTP,resJTP,ensJTP;

	Home(){
		setSize(550,100);
		setLayout(null);
		etudiantBTN = new JButton("Etudiant");add(etudiantBTN).setBounds(10,10,100,30);
		etudiantBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantGUI et = new EtudiantGUI();
				et.setVisible(true);
			}
		});
		
		enseignantBTN = new JButton("Enseignant");add(enseignantBTN).setBounds(140,10,100,30);
		enseignantBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnseignantGUI en = new EnseignantGUI();
				en.setVisible(true);
			}
		});
		
		employeeBTN = new JButton("Employee");add(employeeBTN).setBounds(270,10,100,30);
		employeeBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeGUI em = new EmployeeGUI();
				em.setVisible(true);
			}
		});
		
		resultBTN = new JButton("Result");add(resultBTN).setBounds(400,10,100,30);
		resultBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultGUI r = new resultGUI();
				r.setVisible(true);
			}
		});
		
	}
	public static void main(String[] args) {
		Home h = new Home();
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setVisible(true);
	}
}
