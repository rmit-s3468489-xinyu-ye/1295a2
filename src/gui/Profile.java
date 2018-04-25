/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.ImageIcon;
import mininet.Adult;
import mininet.Person;
import mininet.Relation;

/**
 *
 * @author Yifan ZHANG s3615625
 */
public class Profile extends javax.swing.JFrame 
{

    /**
     * Creates new form Profile
     */
    public Profile(String name) 
    {
        initComponents();
        Person p = MiniNet.driver.getPersonByName(name);
        String path = p.getPhotoPath();
        setFrame();
        
        if (path.equals(""))
        {
            this.jLABimage.setText("No Image");
        }
        else
        {
            this.jLABimage.setIcon(new ImageIcon(path));
        }      
        
        this.jLABname.setText(p.getName());
        this.jLABStatus.setText(p.getStatus());
        
        if(p.getGender()=='M')
        {
            this.jLABGender.setText("Male");
        }
        else
        {
            this.jLABGender.setText("Female");
        }
        //String type conversion
        this.jLABAge.setText(String.valueOf(p.getAge()));
        this.jLABState.setText(p.getState());
            
        if(p instanceof Adult)
        {
            this.jLabF.setVisible(false);
            this.jLabM.setVisible(false);
            this.jLABFather.setVisible(false);
            this.jLABMother.setVisible(false);
        }
        else
        {
            this.jLABFather.setText(getParent(p.getName(), 'M'));
            this.jLABMother.setText(getParent(p.getName(), 'F'));
        }
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(700, 380);
    }
    
    public String getParent(String name,char gender)
    {
        List <Relation> data = MiniNet.driver.getRelations();
        
        for(int i=0;i < data.size();i++)
        {
            Relation r = data.get(i);
            
            if (r.getRelationType().equals("Parent"))
            {
                if(name.equals(r.getName1()))
                {
                   if(MiniNet.driver.getPersonByName(r.getName2()).getGender() == gender)
                       return r.getName2();
                }              
                else if(name.equals(r.getName2()))
                {
                    if(MiniNet.driver.getPersonByName(r.getName1()).getGender() == gender)
                        return r.getName1();
                }                
            }           
        }
        return "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabF = new javax.swing.JLabel();
        jLabM = new javax.swing.JLabel();
        jLABname = new javax.swing.JLabel();
        jLABStatus = new javax.swing.JLabel();
        jLABGender = new javax.swing.JLabel();
        jLABState = new javax.swing.JLabel();
        jLABFather = new javax.swing.JLabel();
        jLABimage = new javax.swing.JLabel();
        jLABAge = new javax.swing.JLabel();
        jLABMother = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name: ");

        jLabel3.setText("Staus: ");

        jLabel4.setText("Gender: ");

        jLabel5.setText("Age: ");

        jLabel6.setText("State: ");

        jLabF.setText("Father: ");

        jLabM.setText("Mother: ");

        jLABimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabF)
                    .addComponent(jLabM)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLABname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLABStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                    .addComponent(jLABGender, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLABFather, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLABState, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLABMother, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLABAge, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLABimage, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLABname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLABStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLABGender, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLABAge, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLABState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)
                                .addComponent(jLABFather, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLABMother, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabM))))
                    .addComponent(jLABimage, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLABAge;
    private javax.swing.JLabel jLABFather;
    private javax.swing.JLabel jLABGender;
    private javax.swing.JLabel jLABMother;
    private javax.swing.JLabel jLABState;
    private javax.swing.JLabel jLABStatus;
    private javax.swing.JLabel jLABimage;
    private javax.swing.JLabel jLABname;
    private javax.swing.JLabel jLabF;
    private javax.swing.JLabel jLabM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}