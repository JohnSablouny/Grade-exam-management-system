import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class DiversControlleur {
public void addEtud(JTextField txt, JComboBox cmb, TreeSet<Etudiant> set) {
	Etudiant etu = new Etudiant(txt.getText(),(Specialite) cmb.getSelectedItem());
	set.add(etu);
	try {
		File f1 = new File("Etudiant.txt");
		FileOutputStream fos = new FileOutputStream(f1);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(set);
		oos.close();
		fos.close();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	
}
}
public void addMatiere(JTextField txt, JComboBox cmb) {
	String filename = cmb.getSelectedItem().toString();
	Matiere m = new Matiere(txt.getText());
	TreeSet<Matiere> set = new TreeSet<Matiere>();
	set.add(m);
	try {
		File f1 = new File(filename+".txt");
		FileOutputStream fos = new FileOutputStream(f1);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(set);
		oos.close();
		fos.close();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	
}
}
public void addSpec(JTextField txt,TreeSet<Specialite> set,DefaultComboBoxModel dml,DefaultComboBoxModel dml2) {
	Specialite s = new Specialite(txt.getText());
	dml.addElement(s);dml2.addElement(s);
	set.add(s);
	try {
	    File f1 = new File("Specialite.txt");
	    FileOutputStream fos = new FileOutputStream(f1);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);

	    oos.writeObject(set);

	    oos.close();
	    fos.close();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
}
public void addCor(JTextField txt,TreeSet<Correcteur> set) {
	Correcteur c = new Correcteur(txt.getText());
	set.add(c);
	try {
		File f1 = new File("Correcteur.txt");
		FileOutputStream fos = new FileOutputStream(f1);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(set);
		oos.close();
		fos.close();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	
}
}
}
