import java.util.*;
import java.io.*;
public class Specialite extends Observable implements Comparable<Specialite>,Serializable{
	String name;
	TreeSet<Matiere> matieres;
	Specialite(String n){
		name=n;matieres=new TreeSet<Matiere>();
		}
	
	public void addMatiere(Matiere m) {
		 matieres.add(m);
		 setChanged();
		 notifyObservers();
	}
	
	public int compareTo(Specialite s) {
		return name.compareTo(s.name);
	}
	public String toString() {
		return name;
	}
}