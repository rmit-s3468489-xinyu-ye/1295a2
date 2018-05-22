package gui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mininet.Adult;
import mininet.NoParentException;
import mininet.Person;
import mininet.TooYoungException;
/**
 *
 * @author Xinyu YE s3468489
 */
public class AddPerson extends javax.swing.JFrame 
{
    /**
     * Creates new form AddPerson
     */
    public AddPerson() 
    {
        initComponents();
        refNameCombos();
        setFrame();      
    }
    
    private void setFrame()
    {
        setResizable(true);
        setLocation(500, 300);
        setSize(470, 385);
    }
    
    private void initComponents() 
    {

        jLabName = new javax.swing.JLabel();
        jLabPhoto = new javax.swing.JLabel();
        jLabStatus = new javax.swing.JLabel();
        jLFN = new javax.swing.JLabel();
        jLMN = new javax.swing.JLabel();
        jTFName = new javax.swing.JTextField();
        jTFPhoto = new javax.swing.JTextField();
        jTFStatus = new javax.swing.JTextField();
        jBAdd = new javax.swing.JButton();
        jBClear = new javax.swing.JButton();
        jLabGender = new javax.swing.JLabel();
        jCBGender = new javax.swing.JComboBox<>();
        jLabAge = new javax.swing.JLabel();
        jTFAge = new javax.swing.JTextField();
        jLabState = new javax.swing.JLabel();
        jCBState = new javax.swing.JComboBox<>();
        jCBFN = new javax.swing.JComboBox<>();
        jCBMN = new javax.swing.JComboBox<>();

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

        jLabName.setText("Name: ");

        jLabPhoto.setText("Profile Photo(Optional) : ");

        jLabStatus.setText("Status(Optional): ");

        jLFN.setText("Father   Name: ");

        jLMN.setText("Mother Name: ");

        jBAdd.setText("Add");
        jBAdd.setToolTipText("");
        
        jBAdd.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBAddActionPerformed(evt);
            }
        });

        jBClear.setText("Clear");
        
        jBClear.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBClearActionPerformed(evt);
            }
        });

        jLabGender.setText("Gender: ");

        jCBGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        jLabAge.setText("Age: ");

        jTFAge.setToolTipText("");

        jLabState.setText("State: ");

        jCBState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFN)
                    .addComponent(jLMN)
                    .addComponent(jLabGender, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabAge)
                    .addComponent(jLabState)
                    .addComponent(jLabName)
                    .addComponent(jLabPhoto)
                    .addComponent(jLabStatus)
                    .addComponent(jBAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBClear, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFAge, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBFN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBMN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabName)
                    .addComponent(jTFName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabPhoto)
                    .addComponent(jTFPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabStatus)
                    .addComponent(jTFStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabGender)
                    .addComponent(jCBGender, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabAge)
                    .addComponent(jTFAge, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabState)
                    .addComponent(jCBState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFN)
                    .addComponent(jCBFN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLMN))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jCBMN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBClear)
                    .addComponent(jBAdd)))
        );

        pack();
    }
    
    /**
     * Clear input for all the fields or reset them to their default values
     * @param evt
     */
    private void jBClearActionPerformed(java.awt.event.ActionEvent evt) 
    {
        this.jTFName.setText("");
        this.jTFPhoto.setText("");
        this.jTFStatus.setText("");
        this.jCBGender.setSelectedIndex(0);
        this.jTFAge.setText("");
        this.jCBState.setSelectedIndex(0);
        this.jCBFN.setSelectedIndex(0);
        this.jCBMN.setSelectedIndex(0);
    }
    
    /**
     * Validate the content that has been input
     * for the age field 
     * 
     * @param str
     * @return 	true if the age field has been filled with an integer
     * 			false if has not been filled with an integer
     */
    private boolean validateAge(String str)
    {
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        if(pattern.matcher(str).matches())
        {  
            return true;
        } 
        else 
        {
            return false;
        }  
    }
    
    /**
     * Validate the content that has been input
     * for the name field
     * 
     * @param str
     * @return 	true if it has been filled with letters and/or integers
     * 			false if it has not been filled with letters and/or integers
     */
    private boolean isString(String str)
    {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[a-zA-Z0-9 ]+");
        java.util.regex.Matcher m = pattern.matcher(str);
        return m.matches();
    }
    
    
    /**
     * Add a new person into the MiniNet
     * 
     * @param evt
     */
    private void jBAddActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String message = "";
        
        if (jTFName.getText().equals(""))//Validate whether the name field has been left blank
        {
            message = "Please enter the name for the person";           
        }
        else if(!isString(jTFName.getText()))
        {
            message = "Please enter a String for the name";
        }
        else if(MiniNet.driver.isPersonExisted(jTFName.getText()))
        {
            message = "This person already exists in MiniNet !";
        }
        else if (jTFAge.getText().equals(""))//Validate whether the age field has been left blank
        {
            message = "Please enter the age for the person";
        }
        else if (!validateAge(jTFAge.getText()))
        {
            message = "Please enter an integer for the age";
        }
        else if ((Integer.parseInt(jTFAge.getText()) < 0 || Integer.parseInt(jTFAge.getText()) > 150) 
        				&& !isString(jTFAge.getText()))//Validate whether the age field has been filled
        {										//with the preset valid age range(0 - 150)
            message = "The age must be between 0 and 150";
        }      
        else
        {
            if((Integer.parseInt(jTFAge.getText())) > MiniNet.AGE)
            {//add a new adult into the MiniNet
                message = MiniNet.driver.addAdult(jTFName.getText(),
                        jTFPhoto.getText(), jTFStatus.getText(), 
                        jCBGender.getSelectedItem().toString().charAt(0), Integer.parseInt(jTFAge.getText()),
                        jCBState.getSelectedItem().toString());
            }
            else
            {
                try 
                {//add a new dependent into the MiniNet
                    message = MiniNet.driver.addDependent(jTFName.getText(),
                            jTFPhoto.getText(), jTFStatus.getText(),
                            jCBGender.getSelectedItem().toString().charAt(0), Integer.parseInt(jTFAge.getText()),
                            jCBState.getSelectedItem().toString(),jCBFN.getSelectedItem().toString(),
                            jCBMN.getSelectedItem().toString());
                    

                } 
                catch (NoParentException npe) 
                {
                    message = npe.getMessage();
                }
            }
        }
   
        JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE);     
    }

    /**
     * Refresh the combo-boxes storing names of male and female adults,
     * respectively
     */
    private void refNameCombos()
    {
        List<Person> mini = MiniNet.driver.getTheMiniNet();
        
        this.jCBFN.removeAllItems();
        this.jCBMN.removeAllItems();
        this.jCBFN.addItem("");
        this.jCBMN.addItem("");
       
        for (int i = 0; i < mini.size(); i++ )
        {
            if(mini.get(i) instanceof Adult)
            {
                if(mini.get(i).getGender() == 'M')
                    this.jCBFN.addItem(mini.get(i).getName());
                else
                    this.jCBMN.addItem(mini.get(i).getName());
            }    
        }
    }
    
    /**
     * If the window is activated,
     * refresh the combo-boxes,
     * in case of new adults been added
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) 
    {
        refNameCombos();
    }

    /**
     * If the window is opened,
     * refresh the combo-boxes,
     * in case of new adults been added
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        refNameCombos();
    }
       
    // Variables declaration
    private javax.swing.JButton jBAdd;
    private javax.swing.JButton jBClear;
    private javax.swing.JComboBox<String> jCBFN;
    private javax.swing.JComboBox<String> jCBGender;
    private javax.swing.JComboBox<String> jCBMN;
    private javax.swing.JComboBox<String> jCBState;
    private javax.swing.JLabel jLFN;
    private javax.swing.JLabel jLMN;
    private javax.swing.JLabel jLabAge;
    private javax.swing.JLabel jLabGender;
    private javax.swing.JLabel jLabName;
    private javax.swing.JLabel jLabPhoto;
    private javax.swing.JLabel jLabState;
    private javax.swing.JLabel jLabStatus;
    private javax.swing.JTextField jTFAge;
    private javax.swing.JTextField jTFName;
    private javax.swing.JTextField jTFPhoto;
    private javax.swing.JTextField jTFStatus;
}
