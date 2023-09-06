import java.util.*;
import java.io.*;
public class Etudiant implements Comparable<Etudiant>,Serializable{
	Specialite sp;
	String name;
	Etudiant(String n,Specialite s){
		sp=s;name=n;
	}
	public int compareTo(Etudiant s) {
		return name.compareTo(s.name);
	}
	public String toString() {
		return name;
	}
}
