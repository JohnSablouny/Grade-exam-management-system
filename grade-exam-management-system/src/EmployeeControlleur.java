import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class EmployeeControlleur {
	public void createExam(JPanel pnl, JComboBox mCMB, JComboBox modCMB, JComboBox sCMB, TreeSet<Examen> examSET, JButton crBTN, JButton clBTN,
            JTextField exTXT, JTextField q1, JTextField q2, JTextField q3, JTextField r1, JTextField r2, JTextField r3,
            JTextField n1, JTextField n2, JTextField n3) {
String fileName = mCMB.getSelectedItem().toString();
pnl.setVisible(true);
Session s = new Session((String) sCMB.getSelectedItem());
String m = (String) modCMB.getSelectedItem();
String name = s.sesType + " " + mCMB.getSelectedItem();
Examen exam = new Examen(name, s, m);
examSET.add(exam);

crBTN.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
 if (!exTXT.getText().isEmpty()) {
     try {
         int exerNumber = Integer.parseInt(exTXT.getText());
         Exercice exer = new Exercice(exerNumber);
         if (!q1.getText().isEmpty() && !q2.getText().isEmpty() && !q3.getText().isEmpty()
                 && !r1.getText().isEmpty() && !r2.getText().isEmpty() && !r3.getText().isEmpty()
                 && !n1.getText().isEmpty() && !n2.getText().isEmpty() && !n3.getText().isEmpty()) {
             Question qu1 = new Question(q1.getText(), Double.parseDouble(n1.getText()), r1.getText());
             Question qu2 = new Question(q2.getText(), Double.parseDouble(n2.getText()), r2.getText());
             Question qu3 = new Question(q3.getText(), Double.parseDouble(n3.getText()), r3.getText());
             exer.addQuest(qu1);
             exer.addQuest(qu2);
             exer.addQuest(qu3);
         }
         exam.addExercice(exer);
         exTXT.setText("");
         q1.setText("");
         q2.setText("");
         q3.setText("");
         r1.setText("");
         r2.setText("");
         r3.setText("");
         n1.setText("");
         n2.setText("");
         n3.setText("");

         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"))) {
             oos.writeObject(examSET);
         } catch (IOException ex) {
             ex.printStackTrace();
         }
     } catch (NumberFormatException ex) {
         System.out.println("Invalid exercise number format: " + exTXT.getText());
     }
 }
}
});

clBTN.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
 pnl.setVisible(false);
}
});

}

	}


