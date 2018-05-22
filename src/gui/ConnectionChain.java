package gui;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mininet.PathNode;
import mininet.Person;
import mininet.Relation;
import mininet.RelationPath;

/**
 *
 * @author Xinyu YE s3468489
 */
public class ConnectionChain extends javax.swing.JFrame 
{

    /** Creates new form ConnectionChain */
    public ConnectionChain() 
    {
        initComponents();
        refCombos();
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(true);
        setLocation(500, 300);
        setSize(600, 425);
    }

    private void initComponents() 
    {
        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabName1 = new javax.swing.JLabel();
        jLabName2 = new javax.swing.JLabel();
        jCBName1 = new javax.swing.JComboBox<>();
        jCBName2 = new javax.swing.JComboBox<>();
        jBQuery = new javax.swing.JButton();

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
                "Relationship"
            }
        ));
        
        jScrollPane.setViewportView(table);

        jLabName1.setText("Please select a person: ");

        jLabName2.setText("Please select another person: ");

        jBQuery.setText("Query");
        
        jBQuery.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBQueryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabName1)
                            .addComponent(jLabName2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBName1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBName2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabName1)
                    .addComponent(jCBName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabName2)
                    .addComponent(jCBName2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    /**
     * To query the closest relation path
     * between the selected two persons
     * 
     * @param evt
     */
    private void jBQueryActionPerformed(java.awt.event.ActionEvent evt) 
    {	//Store all the path nodes in a Hashtable
        Hashtable<String,PathNode> nodes = new Hashtable<String,PathNode>();
        
        //An arrayList that stores all the relation paths
        List<RelationPath> data = new ArrayList<RelationPath>();
        
        creatNodes(nodes);
        
        //Set the first person as the starting node
        PathNode p = (PathNode)nodes.get(this.jCBName1.getSelectedItem().toString());
        
        //Set the second person as the target 
        this.findPath(p,this.jCBName2.getSelectedItem().toString(),nodes,data);
        
        //An arrayList stores all the closet relation path
        //between the starting node and the target
        List<RelationPath> path = new ArrayList<RelationPath>();
        
        for(RelationPath rp : data)
        {	//If the closet relation path list is empty
            if(path.size() == 0)
            {
            	//add all the elements in the relation path list to
            	//the closest relation path list
                path.add(rp);
            }
            else
            {//Compare the length of the two arrayLists
                if(rp.getLength() < path.get(0).getLength())
                {//if the list of the list data is shorter, clear the list path 
                	//and add the former's element to the latter
                    path.clear();
                    path.add(rp);
                }
                else if(rp.getLength() == path.get(0).getLength())
                {//if they are of the same length
                	//add the element of the former to the list path
                    path.add(rp);
                }
            }
        }
        
        //Record the closest relation
        //Concatenate the String format
        for(RelationPath rp:path)
        {
            rp.setPath("Closest Connection: ".concat(rp.getPath()));
        }
        
        this.refTable(data);
    }

    /**
     * If the window is opened,
     * refresh the combo-boxes,
     * in case of new persons been added
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        refCombos();
    }

    /**
     * If the window is activated,
     * refresh the combo-boxes,
     * in case of new persons been added
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) 
    {
        refCombos();
    }
    
    /**
     * Create nodes for every person in the MiniNet
     * @param nodes
     */
    private void creatNodes(Hashtable<String,PathNode> nodes)
    {
        List<Relation> relations = MiniNet.driver.getRelations();
        
        List<Person> persons = MiniNet.driver.getTheMiniNet();
        
        //For every single person, set himself as a path node
        for(Person person:persons)
        {
            PathNode pn = new PathNode(person.getName(),"", null);
            nodes.put(person.getName(), pn);
        }
        
        //Loop through every value in the Hashtable nodes
        for(Object entry: nodes.values())
        {
            PathNode pn = (PathNode)entry;
            
            //Try to match the name of a person
            //with the stored relations
            for(Relation r:relations)
            {
                String name = "";
                
                if(r.getName1().equals(pn.getName()))
                {
                    name = r.getName2();
                }
                else if(r.getName2().equals(pn.getName()))
                {
                    name = r.getName1();
                }
                else
                {//if the name did not match any names
                	//in the stored relations, skip the current
                	//loop and start the next loop
                    continue;
                }
                
                //Find the child node of each path node
                PathNode child = (PathNode)nodes.get(name);
                
                //If some entries of persons are missing
                //possibly due to data mismatching between 
                //the text file 'people.txt' and the database
                if(child == null)
                {//pop up a dialog box to alert the user this situation
                		String message = "Missing data of people !";
                    JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE);
                }
                else if(!pn.getChildren().containsKey(name))
                { //If the child node of a path node does not contain
                    //the requested name
                    //add the requested name as its child node
                    pn.getChildren().put(name,child);     
                }
            }
        }  
    }
    
    /**
     * Find the relation paths between the selected two persons
     * 
     * @param p
     * @param target
     * @param nodes
     * @param data
     */
    private void findPath(PathNode p, String target, Hashtable<String,PathNode> nodes, List<RelationPath> data)
    {
       
        if(p.getParent() == null)
        {//If a path node does not have a parent node
        	//set itself as the start point of the path
            p.setRelation(p.getName());
        }
        else
        {//Record the relation between the the path node and its parent node
            p.setRelation(p.getParent().getRelation()
            		+ "<" + this.getRelationBetween(p.getName(), p.getParent().getName()) 
            		+ ">" +p.getName());
        }
        
        for(Object entry: p.getChildren().values())
        {
        		
            PathNode pn = (PathNode)entry;
            //Set every path node's parent nodes
            pn.setParent(p);
            
            //If the current node's name matches the target
            if(pn.getName().equals(target))
            {//Add this path to the relation path list,
                data.add(new RelationPath(p.getRelation()+ "<"
                        + this.getRelationBetween(pn.getName(), p.getName())
                        + ">" +pn.getName()));
                //and skip the current loop, start the next loop
                continue;
            }
            
            //If failed to find the child node's name of a path node
            if(!(p.getRelation().indexOf(pn.getName()) >= 0))
            {//Recursively invoke the method findPath to find the relation path
                findPath(pn,target,nodes,data);
            }
        }  
    }
    
    /**
     * Return the type of relationship between
     * the selected two persons
     * 
     * @param name1
     * @param name2
     * @return
     */
    private String getRelationBetween(String name1,String name2)
    {
        for(Relation r:MiniNet.driver.getRelations())
        {
            if((r.getName1().equals(name1)&&r.getName2().equals(name2))||
                    (r.getName2().equals(name1)&&r.getName1().equals(name2)))
            {
                return r.getRelationType();
            }
        }
        return "";
    }
    
    /**
     * Refresh the combo-boxes
     * storing names of persons
     * in the MiniNet
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
     * Refresh the table to show the closest relation path
     * between the selected two persons
     * @param data
     */
    private void refTable(List<RelationPath> data)
    { 
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Relationship"};
    
        dtm.setColumnIdentifiers(tableHeads);
        
        //if the two selected people are not connected
        if(data.size()==0)
        {
        		data.add(new RelationPath("Not connected"));
        }
        
        for(int i = 0;i < data.size();i++)
        {

        	Object[] dataRow ={data.get(i).getPath()};

        	dtm.addRow(dataRow);	 
        }            
        table.setModel(dtm);
    }

    // Variables declaration
    private javax.swing.JButton jBQuery;
    private javax.swing.JComboBox<String> jCBName1;
    private javax.swing.JComboBox<String> jCBName2;
    private javax.swing.JLabel jLabName1;
    private javax.swing.JLabel jLabName2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
}
