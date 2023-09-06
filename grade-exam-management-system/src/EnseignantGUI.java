import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.*;
public class EnseignantGUI extends JFrame implements Observer{
	JLabel nameEnLBL,idEnLBL,anneeEnCMBLBL,specEnCMBLBL,matEnCMBLBL;
	JComboBox<String> nameEnCMB,anneeEnsCMB;
	JComboBox<Specialite> specialiteEnsCMB;
	JComboBox<Matiere> matieresEnsCMB;
	DefaultComboBoxModel<String> nameEnsCMBMDL,anneeEnsCMBMDL;
	DefaultComboBoxModel<Specialite>specialiteEnsCMBMDL;
	DefaultComboBoxModel<Matiere> matieresEnsCMBMDL;
	JList<Examen> examEnLST;
	DefaultListModel<Examen> examEnLSTMDL;
	JScrollPane jsp;
	JPanel examPNL;
	Matiere m1 = new Matiere("Web1");
	Matiere m2 = new Matiere("Web2");
	Matiere m3 = new Matiere("Conception mecanique");
	Matiere m4 = new Matiere("Installation et distribution");
	Specialite s1 = new Specialite("Informatique");
	Specialite s2 = new Specialite("Mechanique");
	TreeSet<Etudiant> etSET = new TreeSet<Etudiant>();
	TreeSet<Exercice> exSET = new TreeSet<Exercice>();
	TreeSet<Examen> examSET = new TreeSet<Examen>();
	TreeSet<Matiere> matSET = new TreeSet<Matiere>();
	TreeSet<Specialite> specSET = new TreeSet<Specialite>();
	TreeSet<Question> quesSET = null;
	File f = null;
	JButton corEnsBTN,getEnsExam;
	JComboBox<Correcteur> corEnsCMB;
	DefaultComboBoxModel<Correcteur> corEnsCMBMDL;
	JComboBox<Etudiant> etEnsCMB;
	DefaultComboBoxModel<Etudiant> etEnsCMBMDL;
	JTextField gradeTXT;
	EnseignantControlleur enc = new EnseignantControlleur();	
	EnseignantGUI(){
			
				this.setSize(800,400);				
				this.setLayout(null);
				
				nameEnLBL = new JLabel("Name:");this.add(nameEnLBL).setBounds(10,10,45,25);
				etEnsCMBMDL = new DefaultComboBoxModel<Etudiant>();
				etEnsCMB = new JComboBox<Etudiant>(etEnsCMBMDL);this.add(etEnsCMB).setBounds(60,10,150,25);

				corEnsCMBMDL = new DefaultComboBoxModel<Correcteur>();			
				corEnsCMB = new JComboBox<Correcteur>(corEnsCMBMDL);this.add(corEnsCMB).setBounds(400,10,100,20);
				anneeEnCMBLBL = new JLabel("Correcteur");this.add(anneeEnCMBLBL).setBounds(280,10,150,25);
				
				specialiteEnsCMBMDL = new DefaultComboBoxModel<Specialite>();
				specialiteEnsCMB = new JComboBox<Specialite>(specialiteEnsCMBMDL);this.add(specialiteEnsCMB).setBounds(400,50,150,20);
				specEnCMBLBL = new JLabel("Spécialitées:");this.add(specEnCMBLBL).setBounds(280,50,150,25);
				
				matieresEnsCMBMDL = new DefaultComboBoxModel<Matiere>();
				matieresEnsCMB = new JComboBox<Matiere>(matieresEnsCMBMDL);this.add(matieresEnsCMB).setBounds(400,90,150,20);
				matEnCMBLBL = new JLabel("Matieres:");this.add(matEnCMBLBL).setBounds(280,90,150,25);
				
				
				
				
				
				addWindowListener(new WindowAdapter() {
					public void windowOpened(WindowEvent we) {
						try {
						    File file = new File("Specialite.txt");
						    FileInputStream fis = new FileInputStream(file);
						    ObjectInputStream ois = new ObjectInputStream(fis);

						    Set<Specialite> specSET = (Set<Specialite>) ois.readObject();

						    ois.close();
						    fis.close();

						    for (Specialite spec : specSET) {
						    	specialiteEnsCMBMDL.addElement(spec);;
						    }

						} catch (IOException | ClassNotFoundException e) {
						    e.printStackTrace();
						}
						try {
						    File file = new File("Etudiant.txt");
						    FileInputStream fis = new FileInputStream(file);
						    ObjectInputStream ois = new ObjectInputStream(fis);

						    Set<Etudiant> etSET = (Set<Etudiant>) ois.readObject();

						    ois.close();
						    fis.close();

						    for (Etudiant et : etSET) {
						    	etEnsCMBMDL.addElement(et);;
						    }

						} catch (IOException | ClassNotFoundException e) {
						    e.printStackTrace();
						}
						try {
						    File file = new File("Correcteur.txt");
						    FileInputStream fis = new FileInputStream(file);
						    ObjectInputStream ois = new ObjectInputStream(fis);

						    Set<Correcteur> corSET = (Set<Correcteur>) ois.readObject();

						    ois.close();
						    fis.close();

						    for (Correcteur cor : corSET) {
						    	corEnsCMBMDL.addElement(cor);;
						    }

						} catch (IOException | ClassNotFoundException | ClassCastException e) {
						    e.printStackTrace();
						}
					}
				});
				
				specialiteEnsCMB.addItemListener(new ItemListener() {
				    public void itemStateChanged(ItemEvent e) {
				    	matieresEnsCMBMDL.removeAllElements();
				    	String filename = specialiteEnsCMB.getSelectedItem().toString();
				    	try {
						    File file = new File(filename+".txt");
						    FileInputStream fis = new FileInputStream(file);
						    ObjectInputStream ois = new ObjectInputStream(fis);

						    Set<Matiere> matSET = (Set<Matiere>) ois.readObject();

						    ois.close();
						    fis.close();

						    for (Matiere mat : matSET) {
						    	matieresEnsCMBMDL.addElement(mat);;
						    }

						} catch (IOException | ClassNotFoundException ex) {
						    ex.printStackTrace();
						}
				    }
				});
		
				examEnLSTMDL = new DefaultListModel<Examen>();
				examEnLST = new JList<Examen>(examEnLSTMDL);this.add(examEnLST).setBounds(10,60,200,200);
				getEnsExam = new JButton("Get Exam");this.add(getEnsExam).setBounds(450,150,100,25);
				getEnsExam.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enc.getExamEns(matieresEnsCMB, examEnLST, examEnLSTMDL);
					}
				});
			
				corEnsBTN = new JButton("Correct");this.add(corEnsBTN).setBounds(280,150,100,25);
				corEnsBTN.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	enc.corExam(examEnLST, etEnsCMB, corEnsCMB, matieresEnsCMB, gradeTXT);
				    }
	});
}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
