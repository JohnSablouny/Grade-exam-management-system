import java.util.*;
import java.io.*;
public class Matiere extends Observable implements Comparable<Matiere>,Serializable{
	String nameM;
	TreeSet<Examen> examSET;
	
	public Matiere(String nM) {nameM=nM;examSET=new TreeSet<Examen>();}
	
	public void addExam(Examen ex) {
		 examSET.add(ex);
		 setChanged();
		 notifyObservers();
	}
	public String toString() {return nameM;}
	public int compareTo(Matiere m) {
		return nameM.compareTo(m.nameM);
	}
	}


