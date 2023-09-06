import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;
import java.awt.event.*;
public class DiversGUI extends JPanel implements Observer{
	JPanel div1PNL,div2PNL,div3PNL,div4PNL;
	JButton addDiv1,addDiv2,addDiv3,addDiv4,newDiv1,newDiv2,newDiv3,newDiv4,matDiv,specDiv,etDiv,corDiv;
	JLabel specDivLBL,matDivLBL,etDivLBL,corDivLBL,specDivLBLCMB,specDivLBLCMB2;
	JTextField specDivTXT,matDivTXT,etDivTXT,corDivTXT;
	Specialite s;
	Correcteur c;
	Matiere m;
	Etudiant etu;
	JComboBox<Specialite> specDivCMB,specDivCMB2;
	DefaultComboBoxModel<Specialite> specDivCMBMDL,specDivCMBMDL2;
	EnseignantGUI enG = new EnseignantGUI();
	EtudiantGUI etG = new EtudiantGUI();
	DiversControlleur dg = new DiversControlleur();
	TreeSet<Specialite> spSET = new TreeSet<Specialite>();
	TreeSet<Matiere> mSET = new TreeSet<Matiere>();
	TreeSet<Etudiant> etSET = new TreeSet<Etudiant>();
	TreeSet<Correcteur> cSET = new TreeSet<Correcteur>();
	DiversGUI(){
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);
	specDiv = new JButton("Specialite");this.add(specDiv).setBounds(50,40,100,40);
	specDiv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			div1PNL.setVisible(false);
			div2PNL.setVisible(true);
			div3PNL.setVisible(false);
			div4PNL.setVisible(false);
		}
	});
	div1PNL = new JPanel();div1PNL.setLayout(null);this.add(div1PNL).setBounds(200,40,400,400);div1PNL.setBorder(blackline);
	matDivLBL = new JLabel("Matiere: ");div1PNL.add(matDivLBL).setBounds(10,10,100,25);
	matDivTXT = new JTextField();div1PNL.add(matDivTXT).setBounds(110,10,200,25);
	specDivCMBMDL2 = new DefaultComboBoxModel<Specialite>();
	specDivLBLCMB2 = new JLabel("Specialite");div1PNL.add(specDivLBLCMB2).setBounds(25,50,100,25);
	specDivCMB2 = new JComboBox<Specialite>(specDivCMBMDL2);div1PNL.add(specDivCMB2).setBounds(150,50,150,25);
	addDiv2 = new JButton("Add");div1PNL.add(addDiv2).setBounds(25,100,80,25);
	newDiv1 = new JButton("New");div1PNL.add(newDiv1).setBounds(180,100,80,25);
	div1PNL.setVisible(false);
	
	matDiv = new JButton("Matiere");this.add(matDiv).setBounds(50,100,100,40);
	matDiv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			div2PNL.setVisible(false);
			div1PNL.setVisible(true);
			div3PNL.setVisible(false);
			div4PNL.setVisible(false);
		}
	});
	div2PNL = new JPanel();div2PNL.setLayout(null);this.add(div2PNL).setBounds(200,40,400,400);div2PNL.setBorder(blackline);
	specDivLBL = new JLabel("Specialite: ");div2PNL.add(specDivLBL).setBounds(10,10,100,25);
	specDivTXT = new JTextField();div2PNL.add(specDivTXT).setBounds(110,10,200,25);
	addDiv1 = new JButton("Add");div2PNL.add(addDiv1).setBounds(25,50,80,25);
	newDiv2 = new JButton("New");div2PNL.add(newDiv2).setBounds(180,50,80,25);
	div2PNL.setVisible(false);
	
	etDiv = new JButton("Etudiant");this.add(etDiv).setBounds(50,150,100,40);
	etDiv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			div3PNL.setVisible(true);
			div1PNL.setVisible(false);
			div2PNL.setVisible(false);
			div4PNL.setVisible(false);
		}
	});
	div3PNL = new JPanel();div3PNL.setLayout(null);this.add(div3PNL).setBounds(200,40,400,400);div3PNL.setBorder(blackline);
	etDivLBL = new JLabel("Etudiant: ");div3PNL.add(etDivLBL).setBounds(10,10,100,25);
	etDivTXT = new JTextField();div3PNL.add(etDivTXT).setBounds(110,10,200,25);
	specDivCMBMDL = new DefaultComboBoxModel<Specialite>();
	specDivLBLCMB = new JLabel("Specialite");div3PNL.add(specDivLBLCMB).setBounds(25,50,100,25);
	specDivCMB = new JComboBox<Specialite>(specDivCMBMDL);div3PNL.add(specDivCMB).setBounds(150,50,150,25);
	addDiv3 = new JButton("Add");div3PNL.add(addDiv3).setBounds(25,100,80,25);
	newDiv3 = new JButton("New");div3PNL.add(newDiv3).setBounds(180,100,80,25);
	div3PNL.setVisible(false);
	
	div4PNL = new JPanel();div4PNL.setLayout(null);this.add(div4PNL).setBounds(200,40,400,400);div4PNL.setBorder(blackline);
	corDivLBL = new JLabel("Correcteur: ");div4PNL.add(corDivLBL).setBounds(10,10,100,25);
	corDivTXT = new JTextField();div4PNL.add(corDivTXT).setBounds(110,10,200,25);
	addDiv4 = new JButton("Add");div4PNL.add(addDiv4).setBounds(25,100,80,25);
	newDiv4 = new JButton("New");div4PNL.add(newDiv4).setBounds(180,100,80,25);
	div4PNL.setVisible(false);
	corDiv = new JButton("Correcteur");this.add(corDiv).setBounds(50,200,100,40);
	corDiv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			div3PNL.setVisible(false);
			div1PNL.setVisible(false);
			div2PNL.setVisible(false);
			div4PNL.setVisible(true);
		}
	});

	addDiv1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dg.addSpec(specDivTXT,spSET,specDivCMBMDL,specDivCMBMDL2);
		}
	});
	addDiv2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dg.addMatiere(matDivTXT, specDivCMB2);
		}
	});
	addDiv3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dg.addEtud(etDivTXT, specDivCMB, etSET);
		}
	});
	addDiv4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dg.addCor(corDivTXT, cSET);
		}
	});
}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}