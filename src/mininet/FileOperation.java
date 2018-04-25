/**
 *
 * @author Xinyu YE s3468489
 */
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

public class FileOperation 
{
    
    public static List<Person> readFromFile()
    {
        String line;
        String args[];
        List<Person> people=new ArrayList();
        try
        {
            File f = new File("people.txt");
            
            if (!f.exists() || f.isDirectory())
                f.createNewFile();
            
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
                
                if (Integer.parseInt(args[4]) > 16) 
                {
                    people.add(new Adult(args[0], args[1], args[2], args[3].charAt(0), Integer.parseInt(args[4]), args[5]));
                }
                else
                {
                    people.add(new Dependent(args[0], args[1], args[2], args[3].charAt(0), Integer.parseInt(args[4]), args[5]));         
                }
            }
            //release the resource
            inputStream.close();
        }
        catch (FileNotFoundException ffe)
        {
            ffe.printStackTrace();
            System.out.println("File people.txt was not found");
            System.out.println("or could not be opened");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Fail to read from people.txt");
        }
        return people;
    }
    
    public static List<Relation> readRelation()
    {   
        List<Relation> relations=new ArrayList();
        String line;
        String args[];
        try
        {
            File f = new File("relations.txt");
            
            if (!f.exists() || f.isDirectory())
                f.createNewFile();
            
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
        }
        catch (FileNotFoundException ffe)
        {
            ffe.printStackTrace();
            System.out.println("File relations was not found");
            System.out.println("or could not be opened");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Fail to read from relations");
        }
        
        return relations;
    }
    
    
    public static void writePeopleToFile(List<Person> people)
    { 
        PrintWriter outputStream = null;
        try
        {
            File f = new File("people.txt");
            if(!f.exists() || f.isDirectory())
                f.createNewFile();
            
            outputStream =
                    new PrintWriter(new FileOutputStream("people.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error writing to the file people.txt.");
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println("Unhandled IOException");
            System.exit(0);
        }
        
        for(int i=0; i <people.size(); i++)
        {
            String personInfo="";
            Person p = people.get(i);
            personInfo = p.getName() + ","  + p.getPhotoPath() + "," +
                    p.getStatus() + "," + p.getGender() + "," + p.getAge() +
                    "," + p.getState();
            
            outputStream.println(personInfo);
        }
        outputStream.close( );
    }
    
    public static void writeRelationsToFile(List<Relation> relations)
    {  
        //Collections.sort(relations);
        PrintWriter outputStream = null;
        try
        {
            File f = new File("relations.txt");
            if(!f.exists() || f.isDirectory())
                f.createNewFile();
            
            outputStream =
                    new PrintWriter(new FileOutputStream("relations.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error writing to the file relations.txt.");
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println("Unhandled IOException");
            System.exit(0);
        }
        
        for(int i=0; i < relations.size(); i++)
        {
            String relation = "";
            Relation r = relations.get(i);
            relation = r.getName1() + "," + r.getName2() + "," + r.getRelationType();
            
            outputStream.println(relation);
        }
        outputStream.close( );
    }
    
    /**
     *
     * @param name
     * @param people
     * @return a particular user existed in the ArrayList people
     *
     */
    private static Person getPersonByName(String name, List people)
    {
        for (Object o: people)
        {
            Person p = (Person)o;
            if(p.getName().equals(name))
                return p;
        }
        return null;
    }  
}
