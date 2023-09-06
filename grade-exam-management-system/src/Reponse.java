import java.util.*;
public class Reponse implements Comparable<Reponse>{
	String rep;
	Question q;
	Etudiant e;
	Reponse(String r,Question qu, Etudiant et){
		rep=r;q=qu;e=et;
	}
	public int compareTo(Reponse r) {
		return rep.compareTo(r.rep);
	}
	public String toString() {
		return rep;
	}
}
