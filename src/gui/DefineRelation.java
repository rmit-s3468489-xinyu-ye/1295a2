package gui;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mininet.*;
/**
 *
 * @author Xinyu YE s3468489
 */
public class DefineRelation extends javax.swing.JFrame
{
    /**
     * Creates new form DefineRelation
     */
    public DefineRelation()
    {
        initComponents();
        refTable();
        refCombos();
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(690, 325);
    }
    
    private void initComponents() 
    {

        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jCBName1 = new javax.swing.JComboBox<>();
        jCBName2 = new javax.swing.JComboBox<>();
        jCBRelation = new javax.swing.JComboBox<>();
        jBAsso = new javax.swing.JButton();
        jBDisasso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt) 
            {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) 
            {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Name", "Relationship"
            }
        ));
        
        jScrollPane.setViewportView(table);

        jCBRelation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Classmate", "Colleague", "Couple", "Friend", "Parent", " ", " " }));

        jBAsso.setText("Associate");
        jBAsso.setPreferredSize(new java.awt.Dimension(124, 29));
        
        jBAsso.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBAssoActionPerformed(evt);
            }
        });

        jBDisasso.setText("Disassociate");
        jBDisasso.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBDisassoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCBName1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jCBName2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBRelation, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAsso, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDisasso, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBRelation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBDisasso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBAsso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCBName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCBName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }
    
    /**
     * Associate the selected two persons with
     * a specific type of relationship 
     * 
     * @param evt
     */
    private void jBAssoActionPerformed(java.awt.event.ActionEvent evt) 
    {
        
        String message = "";
        
        //In case the same person has been selected by the user
        if (jCBName1.getSelectedItem().toString().equals(jCBName2.getSelectedItem().toString()))
        {
            message = "Cannot associate a person with itself !";
        }
        else
        {
            Relation relation = new Relation();
            
            relation.setName1(jCBName1.getSelectedItem().toString());
            
            relation.setName2(jCBName2.getSelectedItem().toString());
            
            relation.setRelationType(jCBRelation.getSelectedItem().toString());
            
            message = MiniNet.driver.addRelations(relation);
            
        }
        
        refTable();
        
        JOptionPane.showMessageDialog(null , message ,"Message" , JOptionPane.ERROR_MESSAGE);    
    }
    
    /**
     * If the window is opened,
     * refresh the table,
     * refresh the combo-boxes storing names of persons in MiniNet
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        refTable();
        refCombos();  
    }
    
    /**
     * Disassociate a specific relation between the selected two persons
     * 
     * @param evt
     */
    private void jBDisassoActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String message = "";
        
        //If none of the listed persons have been selected,
        //and the Disassociate button has been clicked
        if(table.getSelectedRow()<0)
        {//pop up a dialog box with an error message,
        	//to prompt the user to select a relation entry,
        	//and terminate this method immediately
            message = "Please select a relationship to disassociate !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Relation r = new Relation();
        //Set the value of the first column of the selected row as the first name of a relation record
        r.setName1(table.getValueAt(table.getSelectedRow(), 0).toString());
        //Set the value of the second column of the selected row as the second name of a relation record
        r.setName2(table.getValueAt(table.getSelectedRow(), 1).toString());
        //Set the value of the third column of the selected row as the type of a relation record
        r.setRelationType(table.getValueAt(table.getSelectedRow(), 2).toString());
        
        if(r.getRelationType().equals("Parent"))
        {
            message = disassociateParent(r);
        }
        else if(r.getRelationType().equals("Couple"))
        {
            try
            {
                message = deCouple(r);
            }
            catch (NoParentException npe)
            {
                message = npe.getMessage();
            }
        }
        else
        {
            message = disassociate(r);
        }
    
        refTable();
       
        JOptionPane.showMessageDialog(null , message ,"Message", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * If the window is activated,
     * refresh the table,
     * refresh the combo-boxes storing names of persons in MiniNet
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) 
    {
        this.refTable();
        this.refCombos();
    }
    
    /**
     * Disassociates a parent-child relationship 
     * between the selected two persons
     * 
     * @param relation
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     */
    private String disassociateParent(Relation relation)
    {
        String message = "";
        
        
        List <Relation> relations = MiniNet.driver.getRelations();
        
        Person child;
        
        if(MiniNet.driver.getPersonByName(relation.getName1()) instanceof Dependent)
        {
            child = MiniNet.driver.getPersonByName(relation.getName1());
        }
        else
        {
            child = MiniNet.driver.getPersonByName(relation.getName2());
        }
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if(r.getRelationType().equals("Parent"))
            {
                if(r.getName1().equals(child.getName()) || r.getName2().equals(child.getName()))
                {
                    relations.remove(r);
                    //restroing the arraylist's size by subtracting 1
                    //from each index after removing the particular object
                    //for looping over all the elements in the arraylist
                    i--;
                    message = "Successfully disassociated them !";
                }
            }
        }
        
        return message;
    }
    
    /**
     * Disassociates a couple relationship 
     * between the selected two persons
     * 
     * @param relation
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     * @throws NoParentException
     */
    private String deCouple(Relation relation) throws NoParentException
    {
        String message = "";
        
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if(relation.getName1().equals(r.getName1()) || relation.getName1().equals(r.getName2()))
            {
                if(r.getRelationType().equals("Parent"))
                    throw new NoParentException();
            }
        }
        
        for (int i = 0; i < relations.size(); i++)
        {
            Relation r = relations.get(i);
            
            if(r.getRelationType().equals("Couple"))
            {
                if(relation.getName1().equals(r.getName1()) || relation.getName1().equals(r.getName2()))
                {
                    relations.remove(r);
                    i--;
                    message = "Successfully decoupled them !";
                    break;
                }
            }
        }
        return message;
    }
    
    /**
     * Dissociates all other relations other than
     * parent or couple
     * 
     * @param relation
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     */
    private String disassociate(Relation relation)
    {
        String message = "";
        
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if(r.getRelationType().equals(relation.getRelationType()))
            {
                if((relation.getName1().equals(r.getName1()) || relation.getName1().equals(r.getName2()))&&
                        (relation.getName2().equals(r.getName1()) || relation.getName2().equals(r.getName2())))
                {
                    relations.remove(r);
                    i--;
                    message = "Successfully disassociated them !";
                    break;
                }
            }
        }
        return message;
    }
    
    /**
     * Refresh the combo-boxes storing names of persons in MiniNet
     */
    private void refCombos()
    {
        List<Person> mini = MiniNet.driver.getTheMiniNet();
        
        this.jCBName1.removeAllItems();
        this.jCBName2.removeAllItems();
        
        for (int i = 0; i < mini.size(); i++ )
        {
            this.jCBName1.addItem(mini.get(i).getName());
            this.jCBName2.addItem(mini.get(i).getName());
        }
    }
    
    /**
     * Refresh the table that displays the existing relations
     */
    private void refTable()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Name", "Name", "Relationship"};
        
        dtm.setColumnIdentifiers(tableHeads);
        
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            {
                Object[] dataRow =
                {r.getName1(),r.getName2(),r.getRelationType()};
                
                dtm.addRow(dataRow);
            }
        }
        table.setModel(dtm);
    }
    
    // Variables declaration
    private javax.swing.JButton jBAsso;
    private javax.swing.JButton jBDisasso;
    private javax.swing.JComboBox<String> jCBName1;
    private javax.swing.JComboBox<String> jCBName2;
    private javax.swing.JComboBox<String> jCBRelation;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
   
}
