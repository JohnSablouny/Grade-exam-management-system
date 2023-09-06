import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class EnseignantControlleur {
	public void getExamEns(JComboBox cmb, JList lst, DefaultListModel lstmdl) {
		Matiere selMat = (Matiere) cmb.getSelectedItem();
		lst.clearSelection();
		String f = cmb.getSelectedItem().toString();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f+".txt"))) {
			TreeSet<Examen> examSET =(TreeSet<Examen>) ois.readObject();
			lstmdl.clear();
			for(Examen examen : examSET) {
		    lstmdl.addElement(examen);
		    }
		    lst.setVisible(true);
		} catch (IOException | ClassNotFoundException | ClassCastException ex) {
		    ex.printStackTrace();
		}
	}
	public void corExam(JList lst,JComboBox etCMB,JComboBox cCMB, JComboBox mCMB,JTextField txt) {
        Examen pe =(Examen) lst.getSelectedValue();
        Etudiant selectedStudent = (Etudiant) etCMB.getSelectedItem();
        Matiere selectedMatiere = (Matiere) mCMB.getSelectedItem();

        ExamPanel ep = new ExamPanel();
        ep.setLayout(null); 
       


        JPanel pnl1 = new JPanel();
        pnl1.setLayout(null);
        ep.setVisible(true);
        JScrollPane jsp = new JScrollPane(pnl1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ep.add(jsp); 
        jsp.setBounds(10, 10, 500, 500);
        int y = 10;

        try (Scanner scanner = new Scanner(new File(selectedStudent.name+"_"+selectedMatiere+ "_exam_answers.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Question: ")) {
                    JLabel questionLBL = new JLabel(line);
                    questionLBL.setBounds(10, y, 500, 20); 
                    pnl1.add(questionLBL);

                    JLabel expectedAnswerLBL = new JLabel(scanner.nextLine());
                    expectedAnswerLBL.setBounds(10, y + 30, 500, 20); 
                    pnl1.add(expectedAnswerLBL);

                    String studentAnswer = scanner.nextLine();
                    if (!studentAnswer.isEmpty()) {
                        JLabel studentAnswerLabel = new JLabel(studentAnswer);
                        studentAnswerLabel.setBounds(10, y + 60, 500, 20); 
                        pnl1.add(studentAnswerLabel);
                    } else {
                        JLabel notAnsweredLBL = new JLabel("Student's Answer: Not answered");
                        notAnsweredLBL.setBounds(10, y + 60, 500, 20); 
                        pnl1.add(notAnsweredLBL);
                    }

                    JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    gradePanel.setBounds(10, y + 90, 500, 30); 

                    JLabel gradeLBL = new JLabel("Grade:");
                    gradePanel.add(gradeLBL);

                    txt = new JTextField(5);
                    gradePanel.add(txt);

                    pnl1.add(gradePanel);

                    y += 140; 
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        }
        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, y + 10, 80, 30); 
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ep.setVisible(false);
            	Matiere selectedMatiere = (Matiere) mCMB.getSelectedItem();
                ArrayList<Double> grades = new ArrayList<>();
                Correcteur cor;
      
                Component[] components = pnl1.getComponents();
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        JPanel gradePanel = (JPanel) component;
                        JTextField gradeTextField = (JTextField) gradePanel.getComponent(1); 
                        String gradeStr = gradeTextField.getText();
                        double grade = Double.parseDouble(gradeStr);
                        grades.add(grade);
                    }
                }
                
                double sum = 0;
                for (double grade : grades) {
                    sum += grade;
                }
                

                String filename =selectedMatiere+"_"+cCMB.getSelectedItem().toString();
                cor = new Correcteur(cCMB.getSelectedItem().toString());
                cor.note = sum;
                
                try{
                	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                        oos.writeObject(cor); 
                    
                    oos.flush();
                } catch (IOException ex) {
                    System.out.println("Error saving grades: " + ex.getMessage());
                }
            }
        });
        pnl1.add(saveButton);


    }
	}


