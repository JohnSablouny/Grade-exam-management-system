import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class resultGUI extends JFrame implements Observer{
	JLabel nameResLBL,matResLBL,specResLBL;
	JComboBox<Etudiant> nameResCMB;
	DefaultComboBoxModel<Etudiant> nameResCMBMDL;
	JComboBox<Matiere> matResCMB;
	DefaultComboBoxModel<Matiere> matResCMBMDL;
	JComboBox<Specialite> specResCMB;
	DefaultComboBoxModel<Specialite> specResCMBMDL;
	JButton getNoteBTN;
	JTable resultTBL;
	DefaultTableModel resultTBLMDL;
	Correction cor;
	ResultControlleur rc = new ResultControlleur();
	resultGUI(){	
		this.setSize(800,400);
		this.setLayout(null);
	
	String[] columnNames = {"Name", "Matiere", "Note"};
	Object[][] data = {
			
	};

	resultTBLMDL = new DefaultTableModel(data, columnNames);
	resultTBL = new JTable(resultTBLMDL);
	JScrollPane scrollPane = new JScrollPane(resultTBL);this.add(scrollPane).setBounds(125,100,500,250);	
	
	nameResLBL = new JLabel("Name:");this.add(nameResLBL).setBounds(20,20,100,25);			
	nameResCMBMDL = new DefaultComboBoxModel<Etudiant>();
	nameResCMB = new JComboBox<Etudiant>(nameResCMBMDL);this.add(nameResCMB).setBounds(60,20,150,25);
	
	matResLBL = new JLabel("Matiere:");this.add(matResLBL).setBounds(240,20,100,25);
	matResCMBMDL = new DefaultComboBoxModel<Matiere>();
	matResCMB = new JComboBox<Matiere>(matResCMBMDL);this.add(matResCMB).setBounds(290,20,150,25);
	
	specResCMBMDL = new DefaultComboBoxModel<Specialite>();
	specResCMB = new JComboBox<Specialite>(specResCMBMDL);this.add(specResCMB).setBounds(510,20,100,25);
	specResLBL = new JLabel("Specialite:");this.add(specResLBL).setBounds(450,20,100,25);
	
	
	getNoteBTN = new JButton("Get Note");this.add(getNoteBTN).setBounds(650,20,90,25);
	getNoteBTN.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	rc.getNote(matResCMB, nameResCMB, resultTBLMDL);
	    }
	});




	
	addWindowListener(new WindowAdapter() {
		public void windowOpened(WindowEvent we) {
			try {
			    File file = new File("Specialite.txt");
			    FileInputStream fis = new FileInputStream(file);
			    ObjectInputStream ois = new ObjectInputStream(fis);

			    Set<Specialite> spSET = (Set<Specialite>) ois.readObject();

			    ois.close();
			    fis.close();

			    for (Specialite sp : spSET) {
			    	specResCMBMDL.addElement(sp);;
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
			    	nameResCMBMDL.addElement(et);;
			    }

			} catch (IOException | ClassNotFoundException e) {
			    e.printStackTrace();
			}
		}
	});
	specResCMB.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	    	matResCMBMDL.removeAllElements();
	    	String filename = specResCMB.getSelectedItem().toString();
	    	try {
			    File file = new File(filename+".txt");
			    FileInputStream fis = new FileInputStream(file);
			    ObjectInputStream ois = new ObjectInputStream(fis);

			    Set<Matiere> matSET = (Set<Matiere>) ois.readObject();

			    ois.close();
			    fis.close();

			    for (Matiere mat : matSET) {
			    	matResCMBMDL.addElement(mat);;
			    }

			} catch (IOException | ClassNotFoundException ex) {
			    ex.printStackTrace();
			}
	    }
	});

	
}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
