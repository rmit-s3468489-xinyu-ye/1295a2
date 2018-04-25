/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mininet.*;

/**
 *
 * @author Xinyu YE s3468489
 */
public class QueryParentChild extends javax.swing.JFrame {

    /**
     * Creates new form QueryParentChild
     */
    public QueryParentChild() 
    {
        initComponents();
        refCombos();
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(400, 300);
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
        table = new javax.swing.JTable();
        jCBName = new javax.swing.JComboBox<>();
        jLName = new javax.swing.JLabel();
        jBParents = new javax.swing.JButton();
        jBChildren = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLName.setText("Please select a person: ");

        jBParents.setText("Find Parents");
        jBParents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBParentsActionPerformed(evt);
            }
        });

        jBChildren.setText("Find Children");
        jBChildren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBChildrenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLName)
                .addGap(96, 96, 96)
                .addComponent(jCBName, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBParents)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBChildren)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBChildren)
                    .addComponent(jBParents))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.refCombos();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.refCombos();
    }//GEN-LAST:event_formWindowOpened

    private void jBParentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBParentsActionPerformed
        
        String message = "";
        
        if(MiniNet.driver.getPersonByName(jCBName.getSelectedItem().toString()) instanceof Adult)
        {
            message = "An adult does not have a parent record !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
        }
        else
            refTable(findParent(jCBName.getSelectedItem().toString()));

    }//GEN-LAST:event_jBParentsActionPerformed

    private void jBChildrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBChildrenActionPerformed
       String message = "";
        
        if(MiniNet.driver.getPersonByName(jCBName.getSelectedItem().toString()) instanceof Dependent)
        {
            message = "A dependent does not have any children !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
        }
        else
        {
            if(findChildren(jCBName.getSelectedItem().toString()).isEmpty())
            {
                message = "This person doe not have any children";
                JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
            }
            else   
                refTable(findChildren(jCBName.getSelectedItem().toString()));
        } 
   
    }//GEN-LAST:event_jBChildrenActionPerformed

    private List<String> findParent(String name)
    { 
        List <Relation> relations = MiniNet.driver.getRelations();
        List <String> result = new ArrayList<String>();

        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if(r.getRelationType().equals("Parent"))
            {
                Person p;
                 
                if(name.equals(r.getName1()))
                {       
                    p = MiniNet.driver.getPersonByName(r.getName2());
                    if(p.getGender()=='M')
                    {
                        result.add("Father: " +r.getName2());
                    }else
                    {
                        result.add("Mother: " +r.getName2());
                    }
                }
                else if(name.equals(r.getName2()))
                {
                    p = MiniNet.driver.getPersonByName(r.getName1());
                    if(p.getGender()=='M')
                    {
                        result.add("Father: " +r.getName1());
                    }else
                    {
                        result.add("Mother: " +r.getName1());
                    }
                }
            }
        }
        return result;        
    }
    
    private List<String> findChildren(String name)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        List <String> result = new ArrayList<String>();

        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
                     
            if(r.getRelationType().equals("Parent"))
            {
                if(name.equals(r.getName1()))
                {            
                    result.add(r.getName2());
                }
                else if(name.equals(r.getName2()))
                {
                    result.add(r.getName1());
                }
            }
        }
        return result;   
    }
    
    
    private void refTable(List<String> result)
    {
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Name"};
    
        dtm.setColumnIdentifiers(tableHeads);  
        
        for(int i = 0;i < result.size();i++)
	{
            Object[] dataRow ={result.get(i)};
            dtm.addRow(dataRow);
	}            
        table.setModel(dtm);
    }
    
    
    private void refCombos()
    {
        List<Person> mini = MiniNet.driver.getTheMiniNet();
        
        this.jCBName.removeAllItems();
  
        for (int i = 0; i < mini.size(); i++ )
        {
            this.jCBName.addItem(mini.get(i).getName());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBChildren;
    private javax.swing.JButton jBParents;
    private javax.swing.JComboBox<String> jCBName;
    private javax.swing.JLabel jLName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}