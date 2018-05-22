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
public class QueryParentChild extends javax.swing.JFrame 
{

    /**
     * Creates new form QueryParentChild
     */
    public QueryParentChild() 
    {
        initComponents();
        refCombo();
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(430, 310);
    }

    private void initComponents() 
    {

        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jCBName = new javax.swing.JComboBox<>();
        jLName = new javax.swing.JLabel();
        jBParents = new javax.swing.JButton();
        jBChildren = new javax.swing.JButton();

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
                "Name"
            }
        ));
        
        jScrollPane.setViewportView(table);

        jLName.setText("Please select a person: ");

        jBParents.setText("Find Parents");
        jBParents.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBParentsActionPerformed(evt);
            }
        });

        jBChildren.setText("Find Children");
        
        jBChildren.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBChildrenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jBParents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBChildren))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jCBName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBParents)
                    .addComponent(jBChildren)))
        );

        pack();
    }
    
    /**
     * If the window is activated,
     * refresh the combo-box storing names of persons,
     * in case of new persons been added
     * 
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) 
    {
        this.refCombo();
    }

    /**
     * If the window is opened,
     * refresh the combo-boxes storing names of persons,
     * in case of new persons been added
     * 
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        this.refCombo();
    }

    /**
     * Find parents of the selected dependent
     * 
     * @param evt
     */
    private void jBParentsActionPerformed(java.awt.event.ActionEvent evt) 
    {
        
        String message = "";
        
        //If the selected person is an adult 
        //and the button "Find Parents" has been clicked
        if(MiniNet.driver.getPersonByName(jCBName.getSelectedItem().toString()) instanceof Adult)
        {
            message = "An adult does not have a parent record !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
        }
        else
            refTable(findParent(jCBName.getSelectedItem().toString()));

    }

    /**
     * Find children of the selected adult
     * 
     * @param evt
     */
    private void jBChildrenActionPerformed(java.awt.event.ActionEvent evt) 
    {
       String message = "";
        
       //If the selected person is a dependent
       //and the button "Find Children" has been clicked
        if(MiniNet.driver.getPersonByName(jCBName.getSelectedItem().toString()) instanceof Dependent)
        {
            message = "A dependent does not have any children !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
        }
        else
        {	//If the selected person is an adult who does not have any children
        		//and the button "Find Children" has been clicked
            if(findChildren(jCBName.getSelectedItem().toString()).isEmpty())
            {
                message = "This person doe not have any children";
                JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
            }
            else 
            {//Refresh the table to display the names of retrieved children
            		//for the selected adult
            		refTable(findChildren(jCBName.getSelectedItem().toString()));
            }
        } 
    }

    /**
     * 
     * @param name
     * @return names of the selected dependent's parents
     */
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
                    
                    
                    if(p.getGender() == 'M')//if the person is male
                    {
                        result.add("Father: " +r.getName2());
                    }
                    else//if the person is female
                    {
                        result.add("Mother: " +r.getName2());
                    }
                }
                else if(name.equals(r.getName2()))
                {
                    p = MiniNet.driver.getPersonByName(r.getName1());
                    
                    if(p.getGender() == 'M')
                    {
                        result.add("Father: " +r.getName1());
                    }
                    else
                    {
                        result.add("Mother: " +r.getName1());
                    }
                }
            }
        }
        return result;        
    }
    
    /**
     *  
     * @param name
     * @return names of the selected adult's children
     */
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
    
    /**
     * Refresh the table to display the retrieved
     * names of parents/children
     * @param result
     */
    private void refTable(List<String> result)
    {
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Name"};
    
        dtm.setColumnIdentifiers(tableHeads);  
        
        for(int i = 0;i < result.size();i++)
	{
            Object[] dataRow = {result.get(i)};
            dtm.addRow(dataRow);
	}            
        table.setModel(dtm);
    }
    
    /**
     * Refresh the combo-box storing names of persons
     */
    private void refCombo()
    {
        List<Person> mini = MiniNet.driver.getTheMiniNet();
        
        this.jCBName.removeAllItems();
  
        for (int i = 0; i < mini.size(); i++ )
        {
            this.jCBName.addItem(mini.get(i).getName());
        }
    }

    // Variables declaration
    private javax.swing.JButton jBChildren;
    private javax.swing.JButton jBParents;
    private javax.swing.JComboBox<String> jCBName;
    private javax.swing.JLabel jLName;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
}
