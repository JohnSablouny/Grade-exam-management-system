import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
public class EtudiantControlleur {
	public void getExam(JComboBox cmb, JList lst) {
        String fileName = cmb.getSelectedItem().toString();
        Matiere selMat = (Matiere) cmb.getSelectedItem();
        
        DefaultListModel<Examen> examLSTMDL = new DefaultListModel<>();
        if(fileName.equals(cmb.getSelectedItem().toString())) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".txt"))) {
            TreeSet<Examen> eS = (TreeSet<Examen>) ois.readObject();
            for (Examen ex : eS) {
                examLSTMDL.addElement(ex);
            }
            lst.setVisible(true);
	        lst.setModel(examLSTMDL);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
	}
	
	public void doExam(JComboBox nCMB, JComboBox mCMB, JList lst) {
		ExamPanel ep = new ExamPanel();
        ep.setLayout(null);
        JPanel pnl = new JPanel();
        pnl.setLayout(null);
        Examen eX =(Examen) lst.getSelectedValue();
        JButton saveExBTN = new JButton("Save");
        pnl.add(saveExBTN).setBounds(150, 10, 70, 25);
        int x = 10, y = 10;
        JLabel examLBL = new JLabel(eX.toString());
        pnl.add(examLBL).setBounds(10, y, 120, 25);
        TreeSet<Exercice> exSET = eX.exSet;
        Iterator<Exercice> exIT = exSET.iterator();
        ArrayList<JTextField> answerFields = new ArrayList<>(); 
        while (exIT.hasNext()) {
            Exercice ex = exIT.next();
            JLabel exLBL = new JLabel("Exercice: " + ex.toString());
            pnl.add(exLBL).setBounds(10, y + 30, 100, 25);
            TreeSet<Question> quesSET = ex.questSET;
            Iterator<Question> qIT = quesSET.iterator();
            while (qIT.hasNext()) {
                Question q = qIT.next();
                JLabel qLBL = new JLabel(q.toString());
                pnl.add(qLBL).setBounds(10, y + 60, 420, 25);
                JTextField qTXT = new JTextField(20);
                pnl.add(qTXT).setBounds(10, y + 90, 420, 25);
                answerFields.add(qTXT); 
                y += 60;
            }
        }

        saveExBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ep.setVisible(false);
                Etudiant etu = (Etudiant) nCMB.getSelectedItem();
                Examen ex = new Examen(eX.examName, eX.ses, eX.modalite);
		        Etudiant selectedStudent = (Etudiant) nCMB.getSelectedItem();
		        Matiere selectedMatiere = (Matiere) mCMB.getSelectedItem();
                Iterator<JTextField> fieldIterator = answerFields.iterator(); 
                for (Exercice exe : eX.exSet) {
                    ex.addExercice(exe);
                    for (Question q : exe.questSET) {
                        JTextField qTXT = fieldIterator.next(); 
                        String ans = qTXT.getText(); 
                        Reponse r = new Reponse(ans, q, etu);
                        q.addReponse(r);
                    }
                }

                String fileName = selectedStudent.name+"_"+selectedMatiere+  "_exam_answers.txt";
                try {
                    File file = new File(fileName);
                    FileWriter writer = new FileWriter(file);

                    for (Exercice exe : ex.exSet) {
                        for (Question q : exe.questSET) {
                            writer.write("Question: " + q.txtQues + "\n");
                            writer.write("Expected Answer: " + q.repCor + "\n");

                            TreeSet<Reponse> r = q.rep;
                            Iterator<Reponse> rIT = r.iterator();
                            while(rIT.hasNext()) {
                            	Reponse rp = rIT.next();
                            if (r != null) {
                                writer.write("Student's Answer: " + rp.rep + "\n");
                            } else {
                                writer.write("Student's Answer: Not answered\n");
                            }
                        }

                            writer.write("\n");
                        }
                    }

                    writer.close();

                } catch (IOException ioe) {
                    System.out.println("An error occurred: " + ioe.getMessage());
                }
            }
        });
        
        JScrollPane jsp = new JScrollPane(pnl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(10, 10, 500, 500);
        ep.add(jsp);
        jsp.setViewportView(pnl);
        ep.setVisible(true);
	}

}
