package gui;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mininet.Adult;
import mininet.Person;
import mininet.Relation;
import mininet.NoParentException;
/**
 *
 * @author Yifan ZHANG s3615625
 */
public class ListEveryone extends javax.swing.JFrame
{
    /**
     * Creates new form ListEveryone
     */
    public ListEveryone()
    {
        initComponents();
        refTable();
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(560, 370);
    }
    
    private void initComponents() 
    {

        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jBProfile = new javax.swing.JButton();
        jBDelete = new javax.swing.JButton();

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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setName("table"); 
        jScrollPane.setViewportView(table);

        jBProfile.setText("View Profile");
        
        jBProfile.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProfileActionPerformed(evt);
            }
        });

        jBDelete.setText("Delete");
        jBDelete.setMaximumSize(new java.awt.Dimension(117, 29));
        jBDelete.setMinimumSize(new java.awt.Dimension(117, 29));
        jBDelete.setPreferredSize(new java.awt.Dimension(117, 29));
        jBDelete.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBProfile)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBProfile))
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * To display the profile of a selected person
     * 
     * @param evt
     */
    private void jBProfileActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String message = "";
        
        //If none of the listed persons have been selected
        //and the view profile button has been clicked
        if(table.getSelectedRow()<0)
        {//pop up a dialog box with an error message
        	//to prompt the user to select  a person
        	//and terminate this method immediately
            message = "Please select a person to view his/her profile !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try
        {
            String name = table.getValueAt(table.getSelectedRow(),0).toString();
            DisplayProfile dp = new DisplayProfile(name);
            dp.setVisible(true);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /**
     * To delete a selected person
     * 
     * @param evt
     */
    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) 
    {
        
        String message = "";
        
        //If none of the listed persons have been selected,
        //and the delete button has been clicked
        if(table.getSelectedRow()<0)
        {//pop up a dialog box with an error message,
        	//to prompt the user to select  a person,
        	//and terminate this method immediately
            message = "Please select a person to delete !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try
        {
            String name = (table.getValueAt(table.getSelectedRow(), 0).toString());
            
            //invoke this method
            //to delete the corresponding relations of a particular person
            deleteRelations(name);
            
            List<Person> data = MiniNet.driver.getTheMiniNet();
            for(int i = 0;i < data.size();i++)
            {
                Person p = data.get(i);
                //delete the corresponding person in the MiniNet
                if(name.equals(p.getName()))
                    data.remove(p);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        //Refresh the table that listing every existing member in MiniNet
        //after deleting a selected person
        refTable();
    }
    
    /**
     * If the window is opened,
     * refresh the table
     * 
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        this.refTable();
    }
    
    /**
     * If the window is activated,
     * refresh the table
     * 
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) 
    {
        this.refTable();
    }
    
    /**
     * Delete relations of a selected person
     * 
     * @param name
     * @throws NoParentException
     */
    private void deleteRelations(String name) throws NoParentException
    {
        //If an adult is involved in a parent-child relationship
        if(getRelationType(name).contains("Parent") &&
                MiniNet.driver.getPersonByName(name) instanceof Adult)
        {
            throw new NoParentException();
        }
        else
        {
            List <Relation> relations = MiniNet.driver.getRelations();
            
            for(int i = 0;i < relations.size();i++)
            {
                Relation r = relations.get(i);
                
                if(name.equals(r.getName1()) || name.equals(r.getName2()))
                {
                    relations.remove(r);
                    //restore the arraylist's size by subtracting 1
                    //from each index after removing the particular object
                    //for looping through all the elements in the arrayList
                    i--;
                }     

            }
        }

    }
    
    /**
     * Return the types of all the relationship that a person
     * is involved in
     * 
     * @param name
     * @return an arrayList storing String objects
     */
    private List<String> getRelationType(String name)
    {
        List<String> relationType = new ArrayList<String>();
        
        for(Relation r:MiniNet.driver.getRelations())
        {
            if(name.equals(r.getName1()) || name.equals(r.getName2()))
            {
                relationType.add(r.getRelationType());
            }
        }
        return relationType;
    }
    
    /**
     * Refresh the table listing persons exist in the MiniNet
     */
    private void refTable()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Name", "PhotoPath", "Status", "Gender", "Age", "State"};
        
        dtm.setColumnIdentifiers(tableHeads);
       
        List<Person> data = MiniNet.driver.getTheMiniNet();
        
        for(int i = 0; i < data.size();i++)
        {
            Person p = data.get(i);
            {
                Object[] dataRow =
                {p.getName(),p.getPhotoPath(),
                    p.getStatus(), p.getGender(), p.getAge(), p.getState()};
                
                dtm.addRow(dataRow);
            }
        }        
        table.setModel(dtm);
    }
    
    // Variables declaration
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBProfile;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
}
