import java.util.*;
import java.io.*;
public class Session implements Serializable{
	String sesType="";
	Session(String sT){sesType=sT;}
	public String toString() {return sesType;}
}

