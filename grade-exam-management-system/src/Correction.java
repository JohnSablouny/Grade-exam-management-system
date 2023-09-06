import java.util.*;
public class Correction{
	Correcteur c1,c2;
	Correction(Correcteur cor1, Correcteur cor2){
		c1=cor1;c2=cor2;
	}
	public double noteFinal() {
		return (c1.note+c2.note)/2;
	}
	public String toString() {
		return "Note Final: "+noteFinal();
	}
}

