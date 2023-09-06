import java.util.*;

public class AnneeUniversitaire extends Observable implements Comparable<AnneeUniversitaire> {
	String numAnnee;
	TreeSet<Session> sessions;

	AnneeUniversitaire(String n) {
		numAnnee = n;
		sessions = new TreeSet<Session>();
	}

	public void addSession(Session s) {
			sessions.add(s);
		setChanged();
		notifyObservers();
	}

	public String toString() {
		return numAnnee;
	}
	
	public int compareTo(AnneeUniversitaire a) {
		return numAnnee.compareTo(a.numAnnee);
	}
}
