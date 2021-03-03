/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mokgadi
 */
public class frmListForm extends javax.swing.JFrame {

    /**
     * Creates new form frmLisForm
     */
    public frmListForm() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    // A LinkedList object to hold integers
    LinkedList<Integer> lst = new LinkedList<>();
    
    // A class that implements a GUI to get input from the user
    // for various operations
    class dialogueGetInput extends JDialog {
        
        public dialogueGetInput(String btnText) {
            
            super(null, btnText+" Element", ModalityType.APPLICATION_MODAL);
            mCreateGUI(btnText);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            this.strAction = btnText;
            this.setVisible(true);
        }
        
        // A variable to hold the string value of a performed action
        String strAction; 
        
        JTextField txtValue = new JTextField(); // to get user value
        JTextField txtIndex = new JTextField(); // to get user specified index
        
        // A method to create the GUI used to get input from the user
        private void mCreateGUI(String strText)
        {
            this.setLayout(new BorderLayout()); // sets the layout of the dialog
            
            // A JPanel component to parent the controls with a border layout
            JPanel jpPanel = new JPanel(new BorderLayout(0, 20)); 
            jpPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            jpPanel.setBackground(new Color(255, 255, 255));
            
            jpPanel.add(mCreateTop(strText), BorderLayout.NORTH);
            jpPanel.add(mCreateBottom());
            this.add(jpPanel);
        }
    
        // This creates the upper part of the Get-Input dialog
        private JPanel mCreateTop(String str)
        {
            // A JPanel component to hold GUI controls
            JPanel jpPanel = new JPanel(new GridLayout(2, 1, 0, 10));
            jpPanel.setBackground(new Color(255, 255, 255)); // sets colour to white

            // Evaluates the string action value against different action buttons
            if(str.equals(btnAdd.getText()) || str.equals(btnAddLast.getText()) ||
                    str.equals(btnAddFirst.getText()) || 
                    str.equals(btnAddWithIndex.getText())) {
                // If an evaluation is true, these GUI controls are added to the JPanel
                // therefore, the GUI
                jpPanel.add(mCreateLine("Integer value", txtValue)); 
            }
            
            // Adds these controls to the GUI only if the instantiating action 
            // origins from btnAddWithIndex - to add an element with an index
            // or btnRemove - remove an element at a given index
            if(str.equals(btnAddWithIndex.getText()) ||
                    str.equals(btnRemove.getText())) {
                
                jpPanel.add(mCreateLine("Specify index", txtIndex));
            }
            return jpPanel;
        }
    
        // Creates the lower part of the Get-Input dialog
        private JPanel mCreateBottom()
        {
            JPanel jpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            jpPanel.setBackground(new Color(255, 255, 255));
            
            // Two Action buttons, Ok and Close, are added to the GUI
            jpPanel.add(mCreateButton("OK", 90, 25, this::mOk));
            jpPanel.add(mCreateSpace());
            jpPanel.add(mCreateButton("Close", 90, 25, this::mClose));
            
            return jpPanel;
        }
    
        // This method uses a JPanel to align a textfield and its label
        private JPanel mCreateLine(String str, JTextField txt)
        {
            JPanel jpPanel = new JPanel(new BorderLayout());
            jpPanel.setBackground(new Color(255, 255, 255));
            JLabel lblLabel = new JLabel(str);
            lblLabel.setPreferredSize(new Dimension(90, 22));
            jpPanel.add(lblLabel, BorderLayout.WEST);
            jpPanel.add(txt);
            return jpPanel;
        }
    
        // A method that creates a button and also assign the button an
        // ActionListener so that it can be able to perform an action, if
        // one is written, when the button is selected.
        private JButton mCreateButton(String str, int width, int height, 
                ActionListener listener)
        {
            JButton btn = new JButton(str);
            btn.addActionListener(listener);
            btn.setBackground(new Color(255, 255, 255));
            btn.setPreferredSize(new Dimension(width, height));
            return btn;
        }
    
        // This method is for purposes of creating the GUI,an empty JLabel
        // is positioned between other controls, to give a capability of
        // controlled use of space
        private JLabel mCreateSpace()
        {
            JLabel lblLabel = new JLabel();
            lblLabel.setPreferredSize(new Dimension(10, 20));
            return lblLabel;
        }
    
        // This method destroys an object of this inner class - Get-Input 
        private void mClose(ActionEvent e)
        {
            this.dispose();
        }
        
        // A method to automatically put a cursor on a text field
        private void mRequestFocus() {
            txtValue.requestFocus();
            if(strAction.equals("Remove")) {
            	txtIndex.requestFocus();
            }
        }
        
        // A method to clear text fields and request focus to dialog
        private void mClear()
        {
            txtValue.setText("");
            txtIndex.setText("");
            mRequestFocus();
        }
    
        // A method that is invoked when the OK button of the GUI has been selected
        private void mOk(ActionEvent e)
        {
            // Evaluates if one of the text field, txtValue or txtIndex, is given an input
            if(!txtValue.getText().equals("") || !txtIndex.getText().equals("")) {
                try{
                    // Using the button action string, the logic decides what case to 
                    // execute depending on the case value
                    switch(strAction) {
                        // A case to add an element to the end of the list
                        case "Add":
                            lst.add(Integer.parseInt(txtValue.getText()));
                            JOptionPane.showMessageDialog(this, "Element added!",
                                    "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                            mClear();
                            break;
                            
                            // A case to add an element to the first index of the list
                        case "Add First":
                            lst.addFirst(Integer.parseInt(txtValue.getText()));
                            JOptionPane.showMessageDialog(this, "First element added!",
                                    "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                            mClear();
                            break;
                            
                            // A case to add an element to top tail of the list 
                        case "Add Last":
                            lst.addLast(Integer.parseInt(txtValue.getText()));
                            JOptionPane.showMessageDialog(this, "Last element added!",
                                    "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                            mClear();
                            break;
                            
                            // A case to add an element at a specified index
                        case "Add (Index)":
                            lst.add(Integer.parseInt(txtIndex.getText()), 
                                    Integer.parseInt(txtValue.getText()));
                            JOptionPane.showMessageDialog(this, "Element at specified index added!", 
                                    "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                            mClear();
                            break;
                            
                            // A case to remove an element at a specified index 
                        case "Remove":
                            // Evaluates if the list size is greater than the input index,
                            // if the index is within bounds of the list
                            if(lst.size() > Integer.parseInt(txtIndex.getText())) {
                                
                                lst.remove(Integer.parseInt(txtIndex.getText()));
                                JOptionPane.showMessageDialog(this, "Element at specified index removed!",
                                        "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                                mClear();
                                
                            } else {
                                JOptionPane.showMessageDialog(this, "The specified index, "+
                                        txtIndex.getText()+" is out of range.",
                                        "WARNING", JOptionPane.WARNING_MESSAGE);
                                mClear();
                            }                            
                            break;
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter integers",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    mRequestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Enter a value", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                mRequestFocus();
            }
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

        jpMainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstIntegerList = new javax.swing.JList<>();
        jpAddCommands = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnAddFirst = new javax.swing.JButton();
        btnAddWithIndex = new javax.swing.JButton();
        lblAddCommands = new javax.swing.JLabel();
        btnAddLast = new javax.swing.JButton();
        jpRemoveAndPrintCommands = new javax.swing.JPanel();
        lblRemoveAndPrindCommands = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        btnRemoveFirst = new javax.swing.JButton();
        btnRemoveLast = new javax.swing.JButton();
        btnPrintList = new javax.swing.JButton();
        lblHeading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setViewportView(lstIntegerList);

        jpAddCommands.setBackground(new java.awt.Color(255, 255, 255));
        jpAddCommands.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAddFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnAddFirst.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddFirst.setText("Add First");
        btnAddFirst.setToolTipText("");
        btnAddFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFirstActionPerformed(evt);
            }
        });

        btnAddWithIndex.setBackground(new java.awt.Color(255, 255, 255));
        btnAddWithIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddWithIndex.setText("Add (Index)");
        btnAddWithIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWithIndexActionPerformed(evt);
            }
        });

        lblAddCommands.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAddCommands.setText("Add Commands:");

        btnAddLast.setBackground(new java.awt.Color(255, 255, 255));
        btnAddLast.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddLast.setText("Add Last");
        btnAddLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAddCommandsLayout = new javax.swing.GroupLayout(jpAddCommands);
        jpAddCommands.setLayout(jpAddCommandsLayout);
        jpAddCommandsLayout.setHorizontalGroup(
            jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAddCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAddCommandsLayout.createSequentialGroup()
                        .addGroup(jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddWithIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(btnAddFirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpAddCommandsLayout.createSequentialGroup()
                        .addComponent(lblAddCommands)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpAddCommandsLayout.setVerticalGroup(
            jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAddCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddCommands)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnAddFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpAddCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWithIndex)
                    .addComponent(btnAddLast))
                .addContainerGap())
        );

        jpRemoveAndPrintCommands.setBackground(new java.awt.Color(255, 255, 255));
        jpRemoveAndPrintCommands.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRemoveAndPrindCommands.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRemoveAndPrindCommands.setText("Remove & Print Commands");

        btnRemove.setBackground(new java.awt.Color(255, 255, 255));
        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnRemoveFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoveFirst.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemoveFirst.setText("Remove First");
        btnRemoveFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFirstActionPerformed(evt);
            }
        });

        btnRemoveLast.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoveLast.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemoveLast.setText("Remove Last");
        btnRemoveLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLastActionPerformed(evt);
            }
        });

        btnPrintList.setBackground(new java.awt.Color(255, 255, 255));
        btnPrintList.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPrintList.setText("Print List");
        btnPrintList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpRemoveAndPrintCommandsLayout = new javax.swing.GroupLayout(jpRemoveAndPrintCommands);
        jpRemoveAndPrintCommands.setLayout(jpRemoveAndPrintCommandsLayout);
        jpRemoveAndPrintCommandsLayout.setHorizontalGroup(
            jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRemoveAndPrintCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRemoveAndPrintCommandsLayout.createSequentialGroup()
                        .addComponent(lblRemoveAndPrindCommands)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpRemoveAndPrintCommandsLayout.createSequentialGroup()
                        .addGroup(jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPrintList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 28, Short.MAX_VALUE)
                        .addGroup(jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnRemoveLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveFirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        jpRemoveAndPrintCommandsLayout.setVerticalGroup(
            jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRemoveAndPrintCommandsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRemoveAndPrindCommands)
                .addGap(18, 18, 18)
                .addGroup(jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove)
                    .addComponent(btnRemoveFirst, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20)
                .addGroup(jpRemoveAndPrintCommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrintList)
                    .addComponent(btnRemoveLast))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lblHeading.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Integer List");

        javax.swing.GroupLayout jpMainPanelLayout = new javax.swing.GroupLayout(jpMainPanel);
        jpMainPanel.setLayout(jpMainPanelLayout);
        jpMainPanelLayout.setHorizontalGroup(
            jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainPanelLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpMainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpAddCommands, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpRemoveAndPrintCommands, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(33, 33, 33))
        );
        jpMainPanelLayout.setVerticalGroup(
            jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblHeading)
                .addGap(33, 33, 33)
                .addGroup(jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpMainPanelLayout.createSequentialGroup()
                        .addComponent(jpAddCommands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jpRemoveAndPrintCommands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // Checks if the list is empty
        if(lst.isEmpty()){
            JOptionPane.showMessageDialog(this, "The list is empty", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        } 
        else{ // if the list is not empty instantiate the dialog Get-Input
            new dialogueGetInput(btnRemove.getText());
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Instantiation of the Get-Input dialog to perform an Add to list action
        new dialogueGetInput(btnAdd.getText());
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFirstActionPerformed
        // Instantiation of the Get-Input dialog to perform an action
        // to Add to first index of the list
        new dialogueGetInput(btnAddFirst.getText());
    }//GEN-LAST:event_btnAddFirstActionPerformed

    private void btnAddLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLastActionPerformed
        // Instantiation of the Get-Input dialog to perform an action
        // to Add to an index immediately after the current last element
        // in the list
        new dialogueGetInput(btnAddLast.getText());
    }//GEN-LAST:event_btnAddLastActionPerformed

    private void btnAddWithIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWithIndexActionPerformed
        // Check if the list is empty
        if(lst.isEmpty()){
            JOptionPane.showMessageDialog(this, "The list is empty. "
                    + "\nThis operation works with a populated list!", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        } 
        else{ // If the list is not empty instantiate Get-Input to add an element
            // at a specified index
            new dialogueGetInput(btnAddWithIndex.getText());
        }
    }//GEN-LAST:event_btnAddWithIndexActionPerformed

    private void btnRemoveFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFirstActionPerformed
        // If the list is not empty the first element in the list is removed
        if(!lst.isEmpty()) {
            lst.removeFirst();
            JOptionPane.showMessageDialog(this, "First element removed!", "MESSAGE",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The list is empty", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveFirstActionPerformed

    private void btnRemoveLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLastActionPerformed
        // If the list is not empty the last element in the list is removed
        if(!lst.isEmpty()) {
            lst.removeLast();
            JOptionPane.showMessageDialog(this, "Last element removed!", "MESSAGE", 
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The list is empty", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveLastActionPerformed

    private void btnPrintListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintListActionPerformed
        // Instatiate a DefaultListModel object
        DefaultListModel model = new DefaultListModel();
        
        if(!lst.isEmpty()) {
            // the DefaultListModel object gets all the element added
            // to the Linkedlist, then it is passed as a model of the
            // JList GUI component of the frmListForm
            for(int i = 0; i < lst.size(); i++) {
                model.addElement(lst.get(i));
            }
            lstIntegerList.setModel(model);
        } else {
            JOptionPane.showMessageDialog(this, "The list is empty", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            lstIntegerList.setModel(model);
        }
    }//GEN-LAST:event_btnPrintListActionPerformed

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
            java.util.logging.Logger.getLogger(frmListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmListForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddFirst;
    private javax.swing.JButton btnAddLast;
    private javax.swing.JButton btnAddWithIndex;
    private javax.swing.JButton btnPrintList;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveFirst;
    private javax.swing.JButton btnRemoveLast;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpAddCommands;
    private javax.swing.JPanel jpMainPanel;
    private javax.swing.JPanel jpRemoveAndPrintCommands;
    private javax.swing.JLabel lblAddCommands;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblRemoveAndPrindCommands;
    private javax.swing.JList<String> lstIntegerList;
    // End of variables declaration//GEN-END:variables
}
