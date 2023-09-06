import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.*;
public class EmployeeGUI extends JFrame implements Observer{

JTabbedPane employeeJTP;
JPanel empPNL,employeeTAB,diversTAB,div1PNL,div2PNL,div3PNL,div4PNL;
JLabel modEmpCMBLBL,sesEmpCMBLBL,matieresEmpCMBLBL,exEmpLBL,quesEmpLBL1,quesEmpLBL2,quesEmpLBL3,noteQ1,noteQ2,noteQ3,repEmpLBL1,repEmpLBL2,repEmpLBL3,
matDivLBL, specDivLBL, etDivLBL, corDivLBL, specDivLBLCMB,specEmpLBLCMB;
JTextField exEmpTXT,quesEmpTXT1,quesEmpTXT2,quesEmpTXT3,exEtdTXT,quesEtdTXT,noteQ1TXT,noteQ2TXT,noteQ3TXT,repEmpTXT1,repEmpTXT2,repEmpTXT3,
matDivTXT, specDivTXT, etDivTXT, corDivTXT;
JComboBox<String> modEmpCMB,sesEmpCMB;
JComboBox<Matiere> matieresEmpCMB;
JComboBox<Specialite> specDivCMB,specEmpCMB;
DefaultComboBoxModel<Specialite> specDivCMBMDL,specEmpCMBMDL;
DefaultComboBoxModel<String> modEmpCMBMDL,sesEmpCMBMDL;
DefaultComboBoxModel<Matiere> matieresEmpCMBMDL;
JButton saveEmpBTN,newEmpBTN,createBTN,closeEmpBTN,
/* Diver */addDiv1, addDiv2, addDiv3, addDiv4, newDiv1, newDiv2,
newDiv3, newDiv4, matDiv, specDiv, etDiv, corDiv, writeDiv1, writeDiv2, writeDiv3, writeDiv4;
Etudiant et = null;
Etudiant etu = null;
Examen exam = null;
Exercice ex;
Question q1 = null,q2=null,q3=null;
TreeSet<Etudiant> etSET = new TreeSet<Etudiant>();
TreeSet<Examen> examSET = new TreeSet<Examen>();
TreeSet<Matiere> matSET = new TreeSet<Matiere>();
TreeSet<Specialite> specSET = new TreeSet<Specialite>();
TreeSet<Correcteur> corSET = new TreeSet<Correcteur>();
TreeSet<Question> quesSET = null;
File f = null;
Matiere m1 = new Matiere("Web1");
Matiere m2 = new Matiere("Web2");
Matiere m3 = new Matiere("Conception mecanique");
Matiere m4 = new Matiere("Installation et distribution");
Matiere m = null;
Specialite s = null;
Correcteur c = null;
EnseignantGUI enG = new EnseignantGUI();
EtudiantGUI etG = new EtudiantGUI();
Exercice exercice;
DiversControlleur dc = new DiversControlleur();
EmployeeControlleur emc = new EmployeeControlleur();
EmployeeGUI(){
	this.setSize(900,700);
	this.setLayout(null);
	employeeJTP = new JTabbedPane();employeeJTP.setSize(700,700);add(employeeJTP).setBounds(10,80,650,550);
	employeeTAB = new JPanel();employeeTAB.setBounds(10,80,700,700);employeeJTP.addTab("Exam", employeeTAB);employeeTAB.setLayout(null);
	employeeTAB.setLayout(null);
	

		
		modEmpCMB = new JComboBox<String>();this.add(modEmpCMB).setBounds(10,40,150,20);
		modEmpCMBMDL = new DefaultComboBoxModel<String>();
		modEmpCMBLBL = new JLabel("Modalite:");this.add(modEmpCMBLBL).setBounds(10,10,150,25);
		modEmpCMBMDL.addElement("Ecrit");modEmpCMBMDL.addElement("Projet");modEmpCMBMDL.addElement("Devoir");
		modEmpCMB.setModel(modEmpCMBMDL);
		
		sesEmpCMB = new JComboBox<String>();this.add(sesEmpCMB).setBounds(170,40,150,20);
		sesEmpCMBMDL = new DefaultComboBoxModel<String>();
		sesEmpCMBLBL = new JLabel("Sessions:");this.add(sesEmpCMBLBL).setBounds(170,10,150,25);
		sesEmpCMBMDL.addElement("Final");sesEmpCMBMDL.addElement("Ratrappage");sesEmpCMBMDL.addElement("Partiel");
		sesEmpCMB.setModel(sesEmpCMBMDL);
		
		matieresEmpCMBMDL = new DefaultComboBoxModel<Matiere>();
		matieresEmpCMB = new JComboBox<Matiere>(matieresEmpCMBMDL);this.add(matieresEmpCMB).setBounds(330,40,150,20);
		matieresEmpCMBLBL = new JLabel("Matieres:");this.add(matieresEmpCMBLBL).setBounds(330,10,150,25);
		
		specEmpCMBMDL = new DefaultComboBoxModel<Specialite>();
		specEmpCMB = new JComboBox<Specialite>(specEmpCMBMDL);this.add(specEmpCMB).setBounds(510,40,150,20);
		specEmpLBLCMB = new JLabel("Specialite");this.add(specEmpLBLCMB).setBounds(490,10,150,25);
		
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
				    	specEmpCMBMDL.addElement(spec);
				    }

				} catch (IOException | ClassNotFoundException e) {
				    e.printStackTrace();
				}
			}
		});
		specEmpCMB.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	matieresEmpCMBMDL.removeAllElements();
		    	String filename = specEmpCMB.getSelectedItem().toString();
		    	try {
				    File file = new File(filename+".txt");
				    FileInputStream fis = new FileInputStream(file);
				    ObjectInputStream ois = new ObjectInputStream(fis);

				    Set<Matiere> matSET = (Set<Matiere>) ois.readObject();

				    ois.close();
				    fis.close();

				    for (Matiere mat : matSET) {
				    	matieresEmpCMBMDL.addElement(mat);;
				    }

				} catch (IOException | ClassNotFoundException ex) {
				    ex.printStackTrace();
				}
		    }
		});
		
		empPNL = new JPanel();employeeTAB.add(empPNL);empPNL.setBounds(10,10,600,500);empPNL.setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		empPNL.setBorder(blackline);empPNL.setVisible(false);
		
		exEmpLBL = new JLabel("Exercice: ");empPNL.add(exEmpLBL).setBounds(10,10,60,25);
		exEmpTXT = new JTextField();empPNL.add(exEmpTXT).setBounds(80,10,40,25);
		
		quesEmpLBL1 = new JLabel("Question 1:");empPNL.add(quesEmpLBL1).setBounds(10,40,80,25);
		quesEmpTXT1 = new JTextField();empPNL.add(quesEmpTXT1).setBounds(100,40,400,25);
		repEmpLBL1 = new JLabel("Reponse:");empPNL.add(repEmpLBL1).setBounds(10,80,60,25);
		repEmpTXT1 = new JTextField();empPNL.add(repEmpTXT1).setBounds(100,80,400,25);
		noteQ1 = new JLabel("Note: ");empPNL.add(noteQ1).setBounds(10,120,60,25);
		noteQ1TXT = new JTextField();empPNL.add(noteQ1TXT).setBounds(100,120,60,25);
		
		quesEmpLBL2 = new JLabel("Question 2:");empPNL.add(quesEmpLBL2).setBounds(10,160,80,25);
		quesEmpTXT2 = new JTextField();empPNL.add(quesEmpTXT2).setBounds(100,160,400,25);
		repEmpLBL2 = new JLabel("Reponse:");empPNL.add(repEmpLBL2).setBounds(10,200,60,25);
		repEmpTXT2 = new JTextField();empPNL.add(repEmpTXT2).setBounds(100,200,400,25);
		noteQ2= new JLabel("Note: ");empPNL.add(noteQ2).setBounds(10,240,60,25);
		noteQ2TXT = new JTextField();empPNL.add(noteQ2TXT).setBounds(100,240,60,25);
		
		quesEmpLBL3 = new JLabel("Question 3:");empPNL.add(quesEmpLBL3).setBounds(10,280,80,25);
		quesEmpTXT3 = new JTextField();empPNL.add(quesEmpTXT3).setBounds(100,280,400,25);
		repEmpLBL3 = new JLabel("Reponse:");empPNL.add(repEmpLBL3).setBounds(10,320,60,25);
		repEmpTXT3 = new JTextField();empPNL.add(repEmpTXT3).setBounds(100,320,400,25);
		noteQ3= new JLabel("Note: ");empPNL.add(noteQ3).setBounds(10,360,60,25);
		noteQ3TXT = new JTextField();empPNL.add(noteQ3TXT).setBounds(100,360,60,25);
		
		createBTN = new JButton("Create Exercice");empPNL.add(createBTN).setBounds(250,400,150,25);
		closeEmpBTN = new JButton("Close");empPNL.add(closeEmpBTN).setBounds(450,400,80,25);
		JButton start = new JButton("Start");this.add(start).setBounds(700,30,150,30);

		start.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	emc.createExam(empPNL, matieresEmpCMB, modEmpCMB, sesEmpCMB, examSET, createBTN, closeEmpBTN, exEmpTXT, quesEmpTXT1, quesEmpTXT2, quesEmpTXT3, repEmpTXT1, repEmpTXT2, repEmpTXT3, noteQ1TXT, noteQ2TXT, noteQ3TXT);
		    }
		});
		employeeJTP.addTab("Divers", new DiversGUI());

}

@Override
public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub
	
}
}
