import java.io.*;
public class Correcteur implements Comparable<Correcteur>,Serializable{
	String name;
	double note;
	public Correcteur(String n) { 
	name=n;
	}
	public String toString() {
		return name;
	}
	public int compareTo(Correcteur c) {
		return name.compareTo(c.name);
	}
}
