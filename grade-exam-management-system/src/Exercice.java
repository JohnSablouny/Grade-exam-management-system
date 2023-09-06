import java.util.*;
import java.io.*;
public class Exercice extends Observable implements Comparable<Exercice>, Serializable{
	int numEx;
	TreeSet<Question> questSET;
	Exercice(int nE){questSET = new TreeSet<Question>();numEx=nE;}
	public String toString() {
		String out=""+numEx;
		return out;
	}
	public void addQuest(Question q) {
		 questSET.add(q);
		 setChanged();
		 notifyObservers();
	}
	
	public int compareTo(Exercice e) {
		return numEx - e.numEx;
	}
}