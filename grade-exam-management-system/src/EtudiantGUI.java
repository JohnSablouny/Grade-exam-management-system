import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class EtudiantGUI extends JFrame {
	JPanel examPNLSPN;
	JLabel nameEtudiantLBL,anneeEtCMBLBL,specEtCMBLBL,matEtCMBLBL,sesEtdCMBLBL;
	JTextField nameEtudiantTXT,idEtudiantTXT,qTXT;
	JComboBox<String> anneeEtCMB;
	JComboBox<Specialite>specialiteEtCMB;
	JComboBox<Matiere> matieresEtCMB;
	JComboBox<String> sesEtdCMB;
	JComboBox<Etudiant> nameEtCMB;
	DefaultComboBoxModel<String> anneeEtCMBMDL;
	DefaultComboBoxModel<Specialite> specialiteEtCMBMDL;
	DefaultComboBoxModel<Matiere> matieresEtCMBMDL;
	DefaultComboBoxModel<String> sesEtdCMBMDL;
	DefaultComboBoxModel<Etudiant> nameEtCMBMDL;
	JTable resultEtdTBL;
	JButton newEtBTN,saveEtBTN,getNoteBTN,getExamsBTN,createBTN,goBTN,saveEmpBTN,newEmpBTN,doExamEtdBTN,writeEtBTN;
	Etudiant et = null;
	Examen exam = null;
	Exercice ex;
	Reponse r;
	JList<Examen> examLST;
	DefaultListModel<Examen> examLSTMDL;
	//Question q1 = null,q2=null,q3=null;	
	TreeSet<Etudiant> etSET = new TreeSet<Etudiant>();
	TreeSet<Exercice> exSET = new TreeSet<Exercice>();
	TreeSet<Examen> examSET = new TreeSet<Examen>();
	TreeSet<Matiere> matSET = new TreeSet<Matiere>();
	TreeSet<Specialite> specSET = new TreeSet<Specialite>();
	TreeSet<Question> quesSET = null;
	File f = null;
	Matiere m = null;
	Examen ex1 = null;
	Correction er;
	Etudiant etu;
	Question q;
	EtudiantControlleur ec = new EtudiantControlleur();
	
	EtudiantGUI(){
				this.setSize(800,400);			
				this.setLayout(null);
			
				
				examLSTMDL = new DefaultListModel<Examen>();
				examLST =new JList<Examen>(examLSTMDL);this.add(examLST).setBounds(10,110,200,170);
				
				nameEtudiantLBL = new JLabel("Name:");this.add(nameEtudiantLBL).setBounds(10,10,45,25);
				nameEtCMBMDL = new DefaultComboBoxModel<Etudiant>();
				nameEtCMB = new JComboBox(nameEtCMBMDL);this.add(nameEtCMB).setBounds(60,10,150,25);
				

				anneeEtCMB = new JComboBox<String>();this.add(anneeEtCMB).setBounds(450,10,100,20);
				anneeEtCMBMDL = new DefaultComboBoxModel<String>();
				anneeEtCMBLBL = new JLabel("Année Universitaire:");this.add(anneeEtCMBLBL).setBounds(300,10,150,25);
				anneeEtCMBMDL.addElement("2020-2021");anneeEtCMBMDL.addElement("2021-2022");anneeEtCMBMDL.addElement("2022-2023");
				anneeEtCMB.setModel(anneeEtCMBMDL);
				
				specialiteEtCMBMDL = new DefaultComboBoxModel<Specialite>();
				specialiteEtCMB = new JComboBox<Specialite>(specialiteEtCMBMDL);this.add(specialiteEtCMB).setBounds(90,70,150,20);
				specEtCMBLBL = new JLabel("Spécialitées:");this.add(specEtCMBLBL).setBounds(10,70,150,25);
				
				matieresEtCMBMDL = new DefaultComboBoxModel<Matiere>();
				matieresEtCMB = new JComboBox<Matiere>(matieresEtCMBMDL);this.add(matieresEtCMB).setBounds(310,70,150,20);
				matEtCMBLBL = new JLabel("Matieres:");this.add(matEtCMBLBL).setBounds(250,70,70,25);
				
			
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
						    	specialiteEtCMBMDL.addElement(spec);;
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
						    	nameEtCMBMDL.addElement(et);;
						    }

						} catch (IOException | ClassNotFoundException e) {
						    e.printStackTrace();
						}
					}
				});
				
				specialiteEtCMB.addItemListener(new ItemListener() {
				    public void itemStateChanged(ItemEvent e) {
				    	matieresEtCMBMDL.removeAllElements();
				    	String filename = specialiteEtCMB.getSelectedItem().toString();
				    	try {
						    File file = new File(filename+".txt");
						    FileInputStream fis = new FileInputStream(file);
						    ObjectInputStream ois = new ObjectInputStream(fis);

						    Set<Matiere> matSET = (Set<Matiere>) ois.readObject();

						    ois.close();
						    fis.close();

						    for (Matiere mat : matSET) {
						    	matieresEtCMBMDL.addElement(mat);;
						    }

						} catch (IOException | ClassNotFoundException ex) {
						    ex.printStackTrace();
						}
				    }
				});
					
				sesEtdCMB = new JComboBox();this.add(sesEtdCMB).setBounds(570,70,120,25);
				sesEtdCMBMDL = new DefaultComboBoxModel<String>();
				sesEtdCMBLBL = new JLabel("Session:");this.add(sesEtdCMBLBL).setBounds(520,70,70,25);
				sesEtdCMBMDL.addElement("Final");sesEtdCMBMDL.addElement("Ratrappage");sesEtdCMBMDL.addElement("Partiel");
				sesEtdCMB.setModel(sesEtdCMBMDL);

				getExamsBTN = new JButton("Get Exams");this.add(getExamsBTN).setBounds(330,130,110,25);
				getExamsBTN.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	ec.getExam(matieresEtCMB, examLST);
				    }
				});			
				
				doExamEtdBTN = new JButton("Do Exam");this.add(doExamEtdBTN).setBounds(550,130,90,25);
				doExamEtdBTN.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	ec.doExam(nameEtCMB, matieresEtCMB, examLST);
				    }
				});
	}
	
}