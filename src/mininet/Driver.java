package mininet;
import gui.MiniNet;
import java.util.Collections;
import java.util.List;
/**
*
* @author Xinyu YE s3468489
*/
public class Driver
{
    private List<Person> theMiniNet;
    private List<Relation> relations;
    private DBConnect dbTest;
    
    public Driver() throws Exception
    {
    		try 
    		{//When launch the program, the data of people
    		//stored in the text file should overwrite the
    		//corresponding data in the database
    			theMiniNet = FileOperation.readPeopleFromFile();		
    		}
    		catch(Exception e)
    		{//If people.txt does not exist, read the data
    		//of people from the database
    			dbTest = new DBConnect();
		    	dbTest.connect();
		    	theMiniNet = dbTest.readPeopleFromDB(); 
    		}
    		
    		//Read the data of relations from releations.txt
    		relations = FileOperation.readRelationsFromFile();
    }
    
    public List<Relation> getRelations()
    {
    		//Alphabetically Sort the names in every entry of relations,
    		//before returning the list of relations
        Collections.sort(relations,Relation.nameComparator);
        return relations;
    }
    
    public void setRelations(List<Relation> relations)
    {
        this.relations = relations;
    }
    
    /**
     * Add different relations into the list relations
     * 
     * @param Relation r
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     */
    public String addRelations(Relation r)
    {
        String message = "";
        
        if (r.getRelationType().equals("Friend"))
        {
            try
            {
                message = makeFriends(r.getName1(), r.getName2());
            }
            catch(TooYoungException tye) 
            {
            		message = tye.getMessage();
            }
            catch (NotToBeFriendsException ntbfe)
            {
                message = ntbfe.getMessage();
            }
        }
        
        if (r.getRelationType().equals("Parent"))
        {
            try
            {
                message = setParents(r.getName1(), r.getName2());
            }
            catch(NoParentException npe)
            {
                message = npe.getMessage();
            }
            
        }
        
        if (r.getRelationType().equals("Couple"))
        {
            try
            {
                message = setSpouse(r.getName1(), r.getName2());
            }
            catch(NoAvailableException nae)
            {
                message = nae.getMessage();
            }
            catch(NotToBeCoupledException ntbe)
            {
                message = ntbe.getMessage();
            }
        }
        
        if (r.getRelationType().equals("Classmate"))
        {
            try
            {
                message = setClassmate(r.getName1(), r.getName2());
            }
            catch (NotToBeClassmatesException nbce)
            {
                message = nbce.getMessage();
            }
        }
        
        if (r.getRelationType().equals("Colleague"))
        {
            try
            {
                message = setColleague(r.getName1(), r.getName2());
            }
            catch (NotToBeColleaguesException nbcle)
            {
                message = nbcle.getMessage();
            }
        }
        return message;
    }
    
    public List<Person> getTheMiniNet()
    {
        return theMiniNet;
    }
    
    public void setTheMiniNet(List<Person> theMiniNet)
    {
    		//Alphabetically sort names of people,
    		//before setting the parameter list theMiniNet to
    		//the field theMiniNet
        Collections.sort(theMiniNet,Person.nameComparator);
        this.theMiniNet = theMiniNet;
    }
    
    /**
     * Make two selected persons as friends
     * 
     * @param name1
     * @param name2
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     * @throws NotToBeFriendsException
     */
    public String makeFriends(String name1, String name2) 
            throws NotToBeFriendsException, TooYoungException
    {
        String message = "";
        
        if(getPersonByName(name1).getAge() <=2
            			|| getPersonByName(name2).getAge() <=2) 
        {//If trying to make friend with a yound child
        		throw new TooYoungException();
        }
        //If trying to make an adult and a dependent friend
        else if(getPersonByName(name1).getClass()!=
           getPersonByName(name2).getClass())
        {
            throw new NotToBeFriendsException();
        }
        else
        {	//If trying to make two children with an age gap
        		//larger than 3 to be friends
            if (getPersonByName(name1) instanceof Dependent
                    && getPersonByName(name2) instanceof Dependent
                    && Math.abs(getPersonByName(name1).getAge()
                            - getPersonByName(name2).getAge()) > 3) 
            {
            		throw new NotToBeFriendsException();
            }
            else
            {	//If the selected two persons are already friends
                if(isFriend(name1, name2))
                {
                    message = "They are already friends !";
                }
                else
                {//If the selected two persons are eligible for being friends
                	//make them friends
                    Relation r = new Relation();
                    
                    r.setName1(compareName(name1,name2,true));
                    r.setName2(compareName(name1,name2,false));
                    r.setRelationType("Friend");
                    
                    this.relations.add(r);
                    Collections.sort(relations,Relation.nameComparator);
                    
                    message = "Successfully make them friends !";
                }
            }
        }
        return message;
    }
    
    /**
     * Set two selected persons as classmates
     * 
     * @param name1
     * @param name2
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     * @throws NotToBeClassmatesException
     */
    public String setClassmate(String name1, String name2) 
            throws NotToBeClassmatesException
    {
        String message = "";
        
        //If trying to involve a young child in a classmate relation
        if(getPersonByName(name1).getAge() <= 2 
                || getPersonByName(name2).getAge() <= 2)
        {
            throw new NotToBeClassmatesException();
        }
        else
        {	//If the selected two persons are already classmates
            if(isClassmate(name1, name2))
            {
                message = "They are already classmates !";
            }
            else
            {
                Relation r = new Relation();
                r.setName1(compareName(name1,name2,true));
                r.setName2(compareName(name1,name2,false));
                r.setRelationType("Classmate");
                
                this.relations.add(r);
                Collections.sort(relations,Relation.nameComparator);
                
                message =  "Successfully set each other as classmate !";
            }
        }
        return message;
    }
    
    /**
     * Set two selected persons as colleagues
     * 
     * @param name1
     * @param name2
     * @return a specific string indicates whether the intended operation
     * 			is successful or not
     * @throws NotToBeColleaguesException
     */
    public String setColleague(String name1, String name2) 
            throws NotToBeColleaguesException
    {
        String message = "";
        
        //If trying to involve a dependent in a colleague relation
        if ((getPersonByName(name1).getClass() !=
             getPersonByName(name2).getClass()) ||
             (getPersonByName(name1) instanceof Dependent &&
              getPersonByName(name2) instanceof Dependent))
        {
            throw new NotToBeColleaguesException();
        }
        else
        {	//If the selected two persons are already colleagues
            if(isColleague(name1, name2))
            {
                message = "They are already colleagues !";
            }
            else
            {
                Relation r = new Relation();
                r.setName1(compareName(name1,name2,true));
                r.setName2(compareName(name1,name2,false));
                r.setRelationType("Colleague");
                
                this.relations.add(r);
                Collections.sort(relations,Relation.nameComparator);
                
                message =  "Successfully set each other as colleague !";
            }
        }
        return message;
    }
    
    /**
     * Set two selected persons as a couple
     * 
     * As stated in the specification,
     * all couples are exclusive to other couples,
     * hence before coupling two selected adults,
     * their spouse's name will be checked.
     * 
     * Any two adults can only be coupled when they are both not 
     * in a couple relationship with others, and we assume that
     * no homosexual couples are allowed in our case, and 
     * there are not any other gender options available other than
     * "M(ale)" and "F(emale)".    
     * 
     * Plus, dependents are not eligible for becoming couples.
     * 
     * @param name1
     * @param name2
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     * @throws NoAvailableException
     * @throws NotToBeCoupledException
     */
    public String setSpouse(String name1, String name2)
            throws NoAvailableException, NotToBeCoupledException
    {
        String message = "";
        
        //If trying to couple an adult and a dependent 
        if(getPersonByName(name1).getClass()
                != getPersonByName(name2).getClass())
        {
            throw new NotToBeCoupledException();
        }
        else if(getPersonByName(name1) instanceof Adult &&
                getPersonByName(name2) instanceof Adult)
        {	//If the selected two adults are already a couple
            if(findSpouse(name1).equals(name2))
            {
                message = "The selected two persons are already a couple !";
            }
            else if(!findSpouse(name1).equals("")||!findSpouse(name2).equals(""))//If both of the selected two adults are not single
            {
                throw new NoAvailableException();
            }
            else
            {//If trying to couple two adults with the same gender
                if(getPersonByName(name1).getGender() ==
                   getPersonByName(name2).getGender())
                {
                    message = "Only people of different genders can be coupled !";
                }
                else
                {
                    Relation r = new Relation();
                    r.setName1(compareName(name1,name2,true));
                    r.setName2(compareName(name1,name2,false));
                    r.setRelationType("Couple");
                    
                    this.relations.add(r);
                    Collections.sort(relations,Relation.nameComparator);
                    
                    message =  "Successfully set each other as spouse";
                }
                
            }
        }
        return message;
    }
    
    /**
     * Set two selected persons
     * in a parent-child relation
     * 
     * @param name1
     * @param name2
     * @return a specific string that indicates whether the intended operation
     * 			is successful or not
     * @throws NoParentException
     */
    public String setParents(String name1, String name2) 
            throws NoParentException
    {
        String message = "";
        
        //If trying to make the same type of person involved
        //in a parent-child relation
        if((getPersonByName(name1).getClass()
                == getPersonByName(name2).getClass()))
        {
            message = "The same type of person cannot"
                    + " be in parent-child relation !";
        }
        else if(getPersonByName(name1) instanceof Adult)
        {	//If trying to add a child to an adult who does not have a spouse
            if(findSpouse(name1).equals(""))
                throw new NoParentException();
            else
            {	//If the selected two persons are already parent-child
                if(isParent(name1, name2))
                {
                    message = "They are already in a"
                            + " parent-child relationship !";
                }
                else
                {
                    Relation r = new Relation();
                    r.setName1(compareName(name1,name2,true));
                    r.setName2(compareName(name1,name2,false));
                    r.setRelationType("Parent");
                    
                    this.relations.add(r);
                    Collections.sort(relations,Relation.nameComparator);
                    
                    Relation r1 = new Relation();
                    name1=findSpouse(name1);
                    r1.setName1(compareName(name1,name2,true));
                    r1.setName2(compareName(name1,name2,false));
                    r1.setRelationType("Parent");
                    
                    this.relations.add(r1);
                    Collections.sort(relations,Relation.nameComparator);
                    
                    message = "Successfully set them as parent-child !"; 
                }
            }
        }
        else if(getPersonByName(name2) instanceof Adult)
        {	//If trying to add a child to an adult who does not have a spouse
            if(findSpouse(name2).equals(""))
                throw new NoParentException();
            else
            {	//If the selected two persons are already parent-child
                if(isParent(name2, name1))
                {
                    message = "They are already in a"
                            + " parent-child relationship !";
                }
                else
                {
                    Relation r = new Relation();
                    r.setName1(compareName(name1,name2,true));
                    r.setName2(compareName(name1,name2,false));
                    r.setRelationType("Parent");

                    this.relations.add(r);
                    Collections.sort(relations,Relation.nameComparator);

                    Relation r1 = new Relation();
                    name2=findSpouse(name2);
                    r1.setName1(compareName(name1,name2,true));
                    r1.setName2(compareName(name1,name2,false));
                    r1.setRelationType("Parent");

                    this.relations.add(r1);
                    Collections.sort(relations,Relation.nameComparator);

                    message = "Successfully set them as parent-child !";
                }
            }
        }
        return message;
    }
     
    /**
     * Test whether two selected persons
     * are already classmates
     * 
     * @param name1
     * @param name2
     * @return true if they are/false if they are not
     */
    private boolean isClassmate(String name1, String name2)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if (r.getRelationType().equals("Classmate"))
            {
                if((r.getName1().equals(name1) && r.getName2().equals(name2))||
                        (r.getName2().equals(name1) && r.getName1().equals(name2)))
                    return true;
            }
        }
        return false;
    }
     
    /**
     * Test whether two selected persons
     * are already colleagues
     * 
     * @param name1
     * @param name2
     * @return true if they are/false if they are not
     */
    private boolean isColleague(String name1, String name2)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if (r.getRelationType().equals("Colleague"))
            {
                if((r.getName1().equals(name1) && r.getName2().equals(name2))||
                        (r.getName2().equals(name1) && r.getName1().equals(name2)))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Test whether two selected persons
     * are already friends
     * 
     * @param name1
     * @param name2
     * @return true if they are/false if they are not
     */
    private boolean isFriend(String name1, String name2)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if (r.getRelationType().equals("Friend"))
            {
                if((r.getName1().equals(name1) && r.getName2().equals(name2))||
                   (r.getName2().equals(name1) && r.getName1().equals(name2)))
                    return true;
            }      
        }  
        return false;
    }
    
    /**
     * Test whether two selected persons
     * are already involved in a parent-child relation
     * 
     * @param name1
     * @param name2
     * @return true if they are/false if they are not
     */
    private boolean isParent(String name1, String name2)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if (r.getRelationType().equals("Parent"))
            {
                if((r.getName1().equals(name1) && r.getName2().equals(name2))||
                        (r.getName2().equals(name1) && r.getName1().equals(name2)))
                    return true;
            }
        }
        return false;
    }
     
    /**
     * Find out a selected person's spouse
     * 
     * @param name
     * @return the name of the selected person's spouse if found/
     * 			an empty string if not found
     */
    private String findSpouse(String name)
    {
        List <Relation> relations = MiniNet.driver.getRelations();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if (r.getRelationType().equals("Couple"))
            {
                if(name.equals(r.getName1()))
                    return r.getName2();
                else if(name.equals(r.getName2()))
                    return r.getName1();
            }
        }
        return "";
    }
    
    
    /**
     * Add a dependent into the MiniNet
     * 
     * @param name
     * @param photoPath
     * @param status
     * @param gender
     * @param age
     * @param state
     * @param fatherName
     * @param motherName
     * @return
     * @throws NoParentException
     */
    public String addDependent(String name, String photoPath, String status,
            char gender, int age, String state,
            String fatherName, String motherName) throws NoParentException
    {
        boolean isFatherExisted;
        boolean isMotherExisted;
        String message = "";
  
        isFatherExisted = isPersonExisted(fatherName);
        isMotherExisted = isPersonExisted(motherName);
        
        if (isFatherExisted && isMotherExisted)//If trying to add a child to two adults
        {
	        	
	        	//If trying to add a child to two adults who are a couple
            if(findSpouse(motherName).equals(fatherName))
            {
                Dependent kid = new Dependent(name, photoPath, status, gender, age, state);
                
                theMiniNet.add(kid);
                //Alphabetically sort the names of persons in MiniNet
                Collections.sort(theMiniNet,Person.nameComparator);
     
                setParents(fatherName, name);
                
                message = "This dependent is successfully added !";
            }
            else
            {//If trying to add a child to two adults who are not a couple
                throw new NoParentException();
            }        
        }
        else
        {//If adding a child to one adult
            throw new NoParentException();
        }
        return message;
    }
    
    /**
     * Add an adult into the MiniNet
     * 
     * @param name
     * @param photoPath
     * @param status
     * @param gender
     * @param age
     * @param state
     * @return
     */
    public String addAdult(String name, String photoPath, String status,
            char gender, int age, String state)
    {
        String message = "";
        
        Person adult = new Adult(name, photoPath, status, gender, age, state);
        
        theMiniNet.add(adult);
        //Alphabetically sort the names of persons in MiniNet
        Collections.sort(theMiniNet,Person.nameComparator);
        
        message = "This adult is successfully added !";
        
        return message;
    }
    
    /**
     * Determine the sequence of the two names to be stored
     * in a relation record
     * 
     * @param name1
     * @param name2
     * @param b
     * @return
     */
    private String compareName(String name1, String name2, boolean b)
    {
        if(b)
        {
        		return name1.compareTo(name2)<=0?name1 : name2;
        }
        else
        {
            return name1.compareTo(name2)>0?name1 : name2;
        }
    }
    
    /**
     * Test whether a person is existed in MiniNet
     * by passing his/her name in as the parameter
     * 
     * @param name
     * @return true if existed/false if non-existed
     */
    public boolean isPersonExisted(String name)
    {
        for (Person p:theMiniNet)
        {
            if(p.getName().equals(name))
                return true;
        }
        return false;
    }
    
    /**
     * Return a person object by passing his/her name in
     * as the parameter
     * 
     * @param name
     * @return the person p if found/null if not found
     */
    public Person getPersonByName(String name)
    {
        for (Person p:theMiniNet)
        {
            if(p.getName().equals(name))
                return p;
        }
        return null;
    }
}