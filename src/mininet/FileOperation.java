package mininet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
*
* @author Xinyu YE s3468489
*/
public class FileOperation 
{	//The visibility of this constant
	//has been set to public, as it is
	//called in the method readFromDB in
	//class DBConnect
    public static int AGE = 16; //Avoid hard coding 
    
    /**
     * Read the data from the file people.txt
     * into the MiniNet
     * 
     * @return an arrayList stores objects of the type Person
     * @throws Exception
     */
    public static List<Person> readPeople() throws Exception
    {
	        String message = "";     
	        String line;
	        String args[];
	        List<Person> people = new ArrayList<Person>();
        
            File f = new File("people.txt");
            
            BufferedReader inputStream =
                    new BufferedReader(new FileReader("people.txt"));
            
            line = "";
            
            while(true)
            {
                line = inputStream.readLine();
                
                if (line == null)
                {
                    break;
                }
                args = line.split(",");
                
                if (Integer.parseInt(args[4]) > AGE)
                {
                    
                    people.add(new Adult(args[0], args[1], args[2],
                            args[3].charAt(0), Integer.parseInt(args[4]), args[5]));                 
                }
                else
                {
                    people.add(new Dependent(args[0], args[1], args[2],
                            args[3].charAt(0), Integer.parseInt(args[4]), args[5]));
                }
            }
            //release the resource
            inputStream.close();
        
        return people;
    }
    
    /**
     * Read the data from the file relations.txt
     * into the MiniNet
     * 
     * @return an arrayList stores objects of the type Relation
     * @throws Exception
     */
    public static List<Relation> readRelations() throws Exception
    {   
        String message = "";
        
        List<Relation> relations = new ArrayList<Relation>();
        String line;
        String args[];

            File f = new File("relations.txt");
         
            BufferedReader inputStream =
                    new BufferedReader(new FileReader("relations.txt"));
            
            line = "";
            
            while(true)
            {
                line = inputStream.readLine();
                
                if (line == null)
                {
                    break;
                }
                args = line.split(",");
                
                Relation relation = new Relation();
                
                relation.setName1(args[0]);
                relation.setName2(args[1]);
                relation.setRelationType(args[2]);
                
                relations.add(relation);
            }
            //release the resource
            inputStream.close();
        return relations;
    }
    
    
    /**
     * Write the arrayList people
     * into the file people.txt
     * 
     * @param people
     */
    public static void writePeopleToFile(List<Person> people) 
    { 
   
    		String message = "";

    		PrintWriter outputStream = null;

    		File f = new File("people.txt");

    		try 
    		{
    			outputStream =
    					new PrintWriter(new FileOutputStream("people.txt"));
    		} 
    		catch (FileNotFoundException e) 
    		{
    			JOptionPane.showMessageDialog(null, e);
    		}

    		for(int i = 0; i <people.size(); i++)
    		{
    			String personInfo ="";
    			Person p = people.get(i);
    			personInfo = p.getName() + ","  + p.getPhotoPath() + "," +
    					p.getStatus() + "," + p.getGender() + "," + p.getAge() +
    					"," + p.getState();

    			outputStream.println(personInfo);
    		}
    		//release resource
    		outputStream.close( );
    }
    
    /**
     * Write the arrayList relations
     * into the file relations.txt
     * 
     * @param relations
     */
    public static void writeRelationsToFile(List<Relation> relations) 
    {  
	    	String message = "";
	
	    	PrintWriter outputStream = null;
	
	    	File f = new File("relations.txt");
	
	    	try 
	    	{
	    		outputStream =
	    				new PrintWriter(new FileOutputStream("relations.txt"));
	    	} 
	    	catch (FileNotFoundException e) 
	    	{
	    		JOptionPane.showMessageDialog(null, e);
	    	}
	
	    	for(int i=0; i < relations.size(); i++)
	    	{
	    		String relation = "";
	    		Relation r = relations.get(i);
	    		relation = r.getName1() + "," + r.getName2() + "," + r.getRelationType();
	
	    		outputStream.println(relation);
	    	}
	    	//release the resource
	    	outputStream.close( );
    }
}
