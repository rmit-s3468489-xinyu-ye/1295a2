package gui;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import mininet.DBConnect;
import mininet.Driver;
import mininet.FileOperation;

/**
 *
 * @author Xinyu YE s3468489
 */
public class MiniNet extends javax.swing.JFrame 
{

    /**
     * Creates new form MiniNet
     */
    private AddPerson addPerson;
    private ConnectionChain cc;
    private DefineRelation define;
    private ListEveryone le;
    private QueryParentChild qpc;
    private QueryRelationship qr;
    public static Driver driver;
    //Using constant to avoid hard coding
    public static final int AGE = 16;
    
    public MiniNet() 
    {
        
        initComponents();
        setFrame();
        try 
        {
            driver = new Driver();
            le = new ListEveryone();
            addPerson = new AddPerson();
            cc = new ConnectionChain();
            define = new DefineRelation();
            qr = new QueryRelationship();
            qpc = new QueryParentChild();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        
        //Set up shortcut keys for menu items
        jMILE.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIAP.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIQR.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIFPC.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMICC.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIR.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(420, 300);
    }

    private void initComponents() 
    {

        jMenuBar = new javax.swing.JMenuBar();
        view = new javax.swing.JMenu();
        jMILE = new javax.swing.JMenuItem();
        jMIAP = new javax.swing.JMenuItem();
        jMIQR = new javax.swing.JMenuItem();
        jMIFPC = new javax.swing.JMenuItem();
        jMICC = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        jMIR = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) 
            {
                try 
                {
					formWindowClosing(evt);
				} 
                catch (Exception e) 
                {
	                	JOptionPane.showMessageDialog(null, e);
	                	System.exit(0);
				}
            }
        });

        view.setText("View");

        jMILE.setText("List Everyone");
        jMILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMILEActionPerformed(evt);
            }
        });
        view.add(jMILE);

        jMIAP.setText("Add a Person");
        jMIAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAPActionPerformed(evt);
            }
        });
        view.add(jMIAP);

        jMIQR.setText("Query Relationship");
        jMIQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIQRActionPerformed(evt);
            }
        });
        view.add(jMIQR);

        jMIFPC.setText("Find Parent/Child");
        jMIFPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIFPCActionPerformed(evt);
            }
        });
        view.add(jMIFPC);

        jMICC.setText("Connection Chain");
        jMICC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICCActionPerformed(evt);
            }
        });
        view.add(jMICC);

        jMenuBar.add(view);

        edit.setText("Edit");

        jMIR.setText("Relationships");
        jMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIRActionPerformed(evt);
            }
        });
        edit.add(jMIR);

        jMenuBar.add(edit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );

        pack();
    }

    private void jMILEActionPerformed(java.awt.event.ActionEvent evt) 
    {
        le.setVisible(true);   
    }

    private void jMIAPActionPerformed(java.awt.event.ActionEvent evt) 
    {
        addPerson.setVisible(true);
    }

    private void jMIRActionPerformed(java.awt.event.ActionEvent evt) 
    {
        define.setVisible(true);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) throws Exception 
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Really exiting?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
	        	FileOperation.writePeopleToFile(driver.getTheMiniNet());
	
	        	FileOperation.writeRelationsToFile(driver.getRelations());
	
	        	System.exit(0);          
        }
        else
        {
            return;
        }
    }

    private void jMIQRActionPerformed(java.awt.event.ActionEvent evt) {
        qr.setVisible(true);
    }

    private void jMIFPCActionPerformed(java.awt.event.ActionEvent evt) {
        qpc.setVisible(true);
    }

    private void jMICCActionPerformed(java.awt.event.ActionEvent evt) {
        cc.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new MiniNet().setVisible(true);
            }
        });
    }

    // Variables declaration 
    private javax.swing.JMenu edit;
    private javax.swing.JMenu view;
    private javax.swing.JMenuItem jMIAP;
    private javax.swing.JMenuItem jMICC;
    private javax.swing.JMenuItem jMIFPC;
    private javax.swing.JMenuItem jMILE;
    private javax.swing.JMenuItem jMIQR;
    private javax.swing.JMenuItem jMIR;
    private javax.swing.JMenuBar jMenuBar;
}
