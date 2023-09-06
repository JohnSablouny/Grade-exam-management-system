import java.util.*;
import java.io.*;
public class Question extends Observable implements Comparable<Question>, Serializable{
	 String txtQues;
	 double noteQuesBar;
	 String repCor;
	 TreeSet<Reponse> rep;
	 
	Question(String tQ,double Nq,String rC){
		txtQues=tQ;noteQuesBar=Nq;repCor=rC;rep=new TreeSet<Reponse>();
	}	
	public String toString() {
		return txtQues+"  Note Question: "+noteQuesBar;
	}
	public void addReponse(Reponse r) {
		rep.add(r);
		setChanged();
		notifyObservers();
	}
	public int compareTo(Question q) {
		return txtQues.compareTo(q.txtQues);
	}
}
