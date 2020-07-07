
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nejdetkadirr
 */
public class Main extends javax.swing.JFrame {
    
    private BufferedReader br;
    private ArrayList<User> userList = new ArrayList<>();
    int[] informations = {0,0,0,0,0,0};
    // index 0 => total man
    // index 1 => total woman
    // index 2 => tall users
    // index 3 => short users
    // index 4 => obese users
    // index 5 => underweight users

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        readFile();        
    }

    public void readFile() {
        userList.clear();
        ((DefaultTableModel) jTable_Users.getModel()).setRowCount(0);
        try {
            br = new BufferedReader(new FileReader(whichOS()));
            for (String line; (line = br.readLine()) != null;) {
                String[] items = line.split(";");
                User user = new User(items[0], items[1], items[2], Integer.parseInt(items[3]), Double.valueOf(items[4]), Double.valueOf(items[5]));
                ((DefaultTableModel) jTable_Users.getModel()).addRow(new Object[] {user.getName(),user.getSurname(),user.getGender(),user.getAge(),user.getLength(),user.getWeight()});
                userList.add(user);
                
                if (user.getGender().equals("Man")) {
                    informations[0]++; 
                } else {
                    informations[1]++; 
                }
                
                if (user.getLength() > 1.80) {
                    informations[2]++;
                } else if (user.getLength() < 1.50) {
                    informations[3]++;
                }
                
                if (user.getBMI() >= 25) {
                    informations[4]++;
                } else if (user.getBMI() < 18.5) {
                    informations[5]++;
                }
                
                
                
                
            }
            br.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
    }

    public void writeFile(String line) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(whichOS(), true)));
            out.println(line);
            out.close();
            readFile();
            clearFields();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    File whichOS() {
        //these paths not supported macOS. Just only supported Ubuntu and Windows.
        File file;
        if (System.getProperty("java.vendor").equals("Ubuntu")) {
            file = (new File(System.getProperty("user.dir") + "/src/users.txt"));
        } else {
            file = (new File(System.getProperty("user.dir") + "\\src\\users.txt"));
        }
        return file;
    }
    
    void clearFields() {
        jTextField_UserName.setText("Name");
        jTextField_UserSurname.setText("Surname");
        jComboBox_UserGender.setSelectedIndex(0);
        jTextField_UserAge.setText("Age");
        jTextField_UserLength.setText("Length");
        jTextField_UserWeight.setText("Weigth");
    }
    
    void message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    boolean ageStatus() {
        try {
            int age = Integer.parseInt(jTextField_UserAge.getText().toString());
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    
    boolean weigthStatus() {
        try {
            int weight = Integer.parseInt(jTextField_UserWeight.getText().toString());
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    
    boolean lengthStatus() {
        String value = jTextField_UserLength.getText().toString();
        char dot = '.';
        try {
            double length = Double.valueOf(value);
            return value.charAt(1) == dot ? false : true;
        } catch (Exception e) {
            return true;
        }       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Users = new javax.swing.JTable();
        jTextField_UserName = new javax.swing.JTextField();
        jTextField_UserSurname = new javax.swing.JTextField();
        jTextField_UserAge = new javax.swing.JTextField();
        jTextField_UserLength = new javax.swing.JTextField();
        jTextField_UserWeight = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox_UserGender = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTable_Users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Gender", "Age", "Length", "Weigth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_Users);
        if (jTable_Users.getColumnModel().getColumnCount() > 0) {
            jTable_Users.getColumnModel().getColumn(0).setResizable(false);
            jTable_Users.getColumnModel().getColumn(1).setResizable(false);
            jTable_Users.getColumnModel().getColumn(2).setResizable(false);
            jTable_Users.getColumnModel().getColumn(3).setResizable(false);
            jTable_Users.getColumnModel().getColumn(4).setResizable(false);
            jTable_Users.getColumnModel().getColumn(5).setResizable(false);
        }

        jTextField_UserName.setText("Name");
        jTextField_UserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_UserNameMouseClicked(evt);
            }
        });

        jTextField_UserSurname.setText("Surname");
        jTextField_UserSurname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_UserSurnameMouseClicked(evt);
            }
        });

        jTextField_UserAge.setText("Age");
        jTextField_UserAge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_UserAgeMouseClicked(evt);
            }
        });

        jTextField_UserLength.setText("Length");
        jTextField_UserLength.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_UserLengthMouseClicked(evt);
            }
        });

        jTextField_UserWeight.setText("Weigth");
        jTextField_UserWeight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_UserWeightMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton1.setText("SAVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox_UserGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Man", "Woman" }));

        jButton2.setText("Calculate BMI of selected user");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Show informations about table");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox_UserGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_UserSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_UserLength, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_UserWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(119, 119, 119)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_UserAge, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_UserAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(2, 2, 2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_UserSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_UserLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_UserWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_UserGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(49, 49, 49)))
                .addComponent(jButton1)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_UserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_UserNameMouseClicked
        // TODO add your handling code here:
        if (jTextField_UserName.getText().toString().equals("Name")) {
            jTextField_UserName.setText("");
        }
    }//GEN-LAST:event_jTextField_UserNameMouseClicked

    private void jTextField_UserSurnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_UserSurnameMouseClicked
        // TODO add your handling code here:
        if (jTextField_UserSurname.getText().toString().equals("Surname")) {
            jTextField_UserSurname.setText("");
        }
    }//GEN-LAST:event_jTextField_UserSurnameMouseClicked

    private void jTextField_UserAgeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_UserAgeMouseClicked
        // TODO add your handling code here:
        if (jTextField_UserAge.getText().toString().equals("Age")) {
            jTextField_UserAge.setText("");
        }
    }//GEN-LAST:event_jTextField_UserAgeMouseClicked

    private void jTextField_UserLengthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_UserLengthMouseClicked
        // TODO add your handling code here:
        if (jTextField_UserLength.getText().toString().equals("Length")) {
            jTextField_UserLength.setText("");
        }
    }//GEN-LAST:event_jTextField_UserLengthMouseClicked

    private void jTextField_UserWeightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_UserWeightMouseClicked
        // TODO add your handling code here:
        if (jTextField_UserWeight.getText().toString().equals("Weigth")) {
            jTextField_UserWeight.setText("");
        }
    }//GEN-LAST:event_jTextField_UserWeightMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField_UserName.getText().toString().length() < 3 || jTextField_UserName.getText().toString().equals("Name")) {
            message("The user's name must to have 3 characters as minimum");
        } else if (jTextField_UserSurname.getText().toString().length() < 2 || jTextField_UserSurname.getText().toString().equals("Surname")) {
            message("The user's surname must to have 2 characters as minimum");
        } else if (ageStatus()) {
            message("Please enter integer value to age field");
        } else if (lengthStatus()) {
            message("Plase enter double value to length filed and The user's length must to have 4 characters as maximum");
        } else if (weigthStatus()) {
            message("Please enter integer value to weigth field");
        } else {
            String userLine = jTextField_UserName.getText().toString()
                    +";"+jTextField_UserSurname.getText().toString()
                    +";"+jComboBox_UserGender.getSelectedItem().toString()
                    +";"+jTextField_UserAge.getText().toString()
                    +";"+jTextField_UserLength.getText().toString()
                    +";"+jTextField_UserWeight.getText().toString();
            writeFile(userLine);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_Users.getSelectedRow();
        if (selectedRow == -1) {
            message("Plase select an user from table");
        } else {
            message("Name : "+userList.get(selectedRow).getName()+" "+userList.get(selectedRow).getSurname()
                    +"\nBMI : " + userList.get(selectedRow).getBMI()
                    +"\nBMI Status : "+userList.get(selectedRow).getBMIStatus());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        message("Total user : "+userList.size()
               +"\nTotal man : "+informations[0]
               +"\nTotal woman : "+informations[1]
               +"\nTall users : "+informations[2]
               +"\nShort users : "+informations[3]
               +"\nObese users : "+informations[4]
               +"\nUnderweight users : "+informations[5]);
                
               // for informations
               // index 0 => total man
               // index 1 => total woman
               // index 2 => tall users
               // index 3 => short users
               // index 4 => obese users
               // index 5 => underweight users
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox_UserGender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Users;
    private javax.swing.JTextField jTextField_UserAge;
    private javax.swing.JTextField jTextField_UserLength;
    private javax.swing.JTextField jTextField_UserName;
    private javax.swing.JTextField jTextField_UserSurname;
    private javax.swing.JTextField jTextField_UserWeight;
    // End of variables declaration//GEN-END:variables
}
