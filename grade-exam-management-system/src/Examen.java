import java.util.*;
import java.io.*;
public class Examen extends Observable implements Comparable<Examen>,Serializable{
	String examName;
	Session ses;
	String modalite;//class
	TreeSet<Exercice> exSet; // set


	public Examen(String en,Session s,String mod) {
		exSet = new TreeSet<Exercice>();
		examName = en;
		ses = s;
		modalite = mod;
	}

	public void addExercice(Exercice e) {
			exSet.add(e);
		setChanged();
		notifyObservers();
	}

	public int compareTo(Examen e) {
		return examName.compareTo(e.examName);
	}

	public String toString() {
		return examName;
	}
}

